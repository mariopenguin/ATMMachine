package com.example.ATM.Database.Customers;

import com.example.ATM.Models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final ICustomerRepository iCustomerRepository;
    public CustomerService(ICustomerRepository iCustomerRepository){
        this.iCustomerRepository = iCustomerRepository;
    }
    public Customer findCustomerById(Integer id){
        List<Customer> customers = (List<Customer>) iCustomerRepository.findAll();
        for (Customer c: customers) {
            if (c.getCustomerId() == id)
                return c;
        }
        return null;
    }
}
