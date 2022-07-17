package com.example.ATM.Controllers;

import com.example.ATM.Database.ATMMachines.ATMService;
import com.example.ATM.Database.ATMMachines.IATMRepository;
import com.example.ATM.Models.ATMMachine;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ATMController {

    private final IATMRepository iatmRepository;
    public ATMController(IATMRepository iatmRepository){
        this.iatmRepository = iatmRepository;
    }

    @RequestMapping("api/atm/hello")
    @GetMapping
    public String sayHello() {
        return "Hello world";
    }

    @RequestMapping("api/atm/listAll")
    @GetMapping
    public String info(){
        return iatmRepository.findAll().toString();
    }

    @RequestMapping("api/atm/createAtm")
    @PostMapping
    public String createAtm(){
        HashMap<Integer,Integer> bills = new HashMap<Integer,Integer>();
        bills.put(50,10);
        bills.put(20,30);
        bills.put(10,30);
        bills.put(5,20);
        ATMMachine atmMachine = new ATMMachine(1,1500,500,bills);
        iatmRepository.save(atmMachine);
        return atmMachine.toString();
    }

    @RequestMapping("api/atm/")
    @GetMapping
    public String info(@RequestParam(name="id") Integer id){
        ATMService atmService = new ATMService(iatmRepository);
        ATMMachine atmMachine = atmService.findATMById(id);
        if(id == atmMachine.getAccountNumber())
            return atmMachine.toString();
        else{
            return "No ATMMachine found with that Id";
        }
    }
}
