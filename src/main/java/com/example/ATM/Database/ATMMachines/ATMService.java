package com.example.ATM.Database.ATMMachines;

import com.example.ATM.Database.Customers.ICustomerRepository;
import com.example.ATM.Models.ATMMachine;
import com.example.ATM.Models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATMService {
    private final IATMRepository iatmRepository;
    public ATMService(IATMRepository iatmRepository){
        this.iatmRepository = iatmRepository;
    }
    public ATMMachine findATMById(Integer id){
        List<ATMMachine> atmMachines = (List<ATMMachine>) iatmRepository.findAll();
        for (ATMMachine c: atmMachines) {
            if (c.getAccountNumber() == id)
                return c;
        }
        return null;
    }
}