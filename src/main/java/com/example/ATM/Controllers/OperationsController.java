package com.example.ATM.Controllers;


import com.example.ATM.Database.ATMMachines.ATMService;
import com.example.ATM.Database.ATMMachines.IATMRepository;
import com.example.ATM.Database.Customers.CustomerService;
import com.example.ATM.Database.Customers.ICustomerRepository;
import com.example.ATM.Database.Operations.IOperationsService;
import com.example.ATM.Exceptions.ElementNotFoundException;
import com.example.ATM.Exceptions.IncorrectPinException;
import com.example.ATM.Exceptions.NotEnoughFundsException;
import com.example.ATM.Models.ATMMachine;
import com.example.ATM.Models.Customer;
import com.example.ATM.Models.FundsDispension;
import org.springframework.web.bind.annotation.*;

@RestController
public class OperationsController {
    private final IOperationsService iOperationsRepository;
    private final ICustomerRepository iCustomerRepository;
    private final IATMRepository iatmRepository;

    public OperationsController(IOperationsService iOperationsRepository, ICustomerRepository iCustomerRepository, IATMRepository iatmRepository) {
        this.iOperationsRepository = iOperationsRepository;
        this.iCustomerRepository = iCustomerRepository;
        this.iatmRepository = iatmRepository;
    }

    @RequestMapping("api/operations/dispense")
    @PostMapping
    public String dispenseFounds(@RequestParam(name = "customerId") Integer customerId, @RequestParam(name = "customerPin") Integer pin, @RequestParam(name = "atmId") Integer atmId, @RequestParam(name = "fundsToDispense") Integer fundsToDispense) {
        Customer customer = new CustomerService(iCustomerRepository).findCustomerById(customerId);
        ATMMachine atmMachine = new ATMService(iatmRepository).findATMById(atmId);
        try{
            if (customer==null || atmMachine == null)
                throw new ElementNotFoundException("User or ATM Ids wrong");
            if (pin!= customer.getPin())
                throw new IncorrectPinException();
            if(fundsToDispense> customer.getCurrentFunds())
                throw new NotEnoughFundsException("You don't have enought funds!");
            if(fundsToDispense> customer.getMaxWithdrawal())
                return "Max withdrawal exceeded";
            if(atmMachine.getCurrentFounds()<fundsToDispense)
                throw new NotEnoughFundsException("The atm machine does not have enough founds :(");
        }
        catch (Exception ex){
            return ex.getMessage();
        }

        //If we have enough founds and the ATM Machine too, we proceed to make the operation
        customer.retireFunds(fundsToDispense); //Remove the funds to the customer
        FundsDispension fundsDispension = null; //Remove the funds to the atmMachine and expend the funds.
        try {
            fundsDispension = atmMachine.dispenseMoney(fundsToDispense);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        fundsDispension.setCustomerId(customer.getCustomerId());
        fundsDispension.setAtmMachineId(atmMachine.getAccountNumber());

        //Save all the objects to the database
        iOperationsRepository.save(fundsDispension);
        iatmRepository.save(atmMachine);
        iCustomerRepository.save(customer);

        return fundsDispension.toString()+" remaining funds: "+customer.getCurrentFunds();
    }
}
