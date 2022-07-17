package com.example.ATM.Controllers;

import com.example.ATM.Database.Customers.CustomerService;
import com.example.ATM.Database.Customers.ICustomerRepository;
import com.example.ATM.Models.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final ICustomerRepository iCustomerRepository;
    public UserController(ICustomerRepository iCustomerRepository){
        this.iCustomerRepository = iCustomerRepository;
    }

    @RequestMapping("api/user/listUsers")
    @GetMapping
    public List<Customer> listUsers(){
      return (List<Customer>) iCustomerRepository.findAll();
    }

    @RequestMapping("api/user/showUserInfo")
    @GetMapping
    public String userInfo(@RequestParam(name = "id") Integer id,@RequestParam(name = "pin") Integer pin){
        CustomerService c = new CustomerService(iCustomerRepository);
        Customer customer = c.findCustomerById(id);
        if (customer!= null && customer.getPin()==pin)
            return customer.showCurrentFunds();
        return "Incorrect ID or PIN, check IDs in /api/user/listUsers";
    }
    @RequestMapping("api/user/createUser")
    @PostMapping
    public String createCustomer(@RequestParam String name,@RequestParam Integer pin,@RequestParam Integer currentFunds,@RequestParam Integer maxWithDrawal){
        Customer customer = new Customer(name,pin,currentFunds,maxWithDrawal);
        iCustomerRepository.save(customer);
        return customer.toString();
    }

}
