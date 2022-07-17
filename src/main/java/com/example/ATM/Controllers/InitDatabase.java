package com.example.ATM.Controllers;


import com.example.ATM.Database.ATMMachines.IATMRepository;
import com.example.ATM.Database.Customers.CustomerService;
import com.example.ATM.Database.Customers.ICustomerRepository;
import com.example.ATM.Models.ATMMachine;
import com.example.ATM.Models.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class InitDatabase {

    private final IATMRepository iatmRepository;
    private final ICustomerRepository iCustomerRepository;

    public InitDatabase(IATMRepository iatmRepository, ICustomerRepository iCustomerRepository) {
        this.iatmRepository = iatmRepository;
        this.iCustomerRepository = iCustomerRepository;
    }
    @RequestMapping("api/db")
    @GetMapping
    public String initDB() {

        CustomerService customerService = new CustomerService(iCustomerRepository);
        Customer customer = new Customer("Ruben",111,50,500);
        Customer customer1 = new Customer("Maria",111,550,500);
        Customer customer2 = new Customer("Juan",111,250,500);
        Customer customer3 = new Customer("Angelica",111,450,500);
        Customer customer4 = new Customer("Edgar",111,9050,500);
        iCustomerRepository.save(customer);
        iCustomerRepository.save(customer1);
        iCustomerRepository.save(customer2);
        iCustomerRepository.save(customer3);
        iCustomerRepository.save(customer4);

        HashMap<Integer,Integer> bills = new HashMap<Integer,Integer>();
        bills.put(50,10);
        bills.put(20,30);
        bills.put(10,30);
        bills.put(5,20);
        iatmRepository.save(new ATMMachine(1,1500,500,bills));
        return "Sucess!";
    }
}
