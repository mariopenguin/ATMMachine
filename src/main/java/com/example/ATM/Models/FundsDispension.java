package com.example.ATM.Models;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name="Operations")
public class FundsDispension {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer operationId;
    private Integer customerId;
    private Integer atmMachineId;
    private Integer foundsDispensed;
    private HashMap<Integer,Integer> numberOfNotes;

    public FundsDispension() {
        this.numberOfNotes = new HashMap<Integer,Integer>();
        for (EMoneyBills i: EMoneyBills.values())
            this.numberOfNotes.put(i.getValue(),0);
        this.foundsDispensed = 0;
    }

    public void addNewBill(Integer i){
        numberOfNotes.put(i,numberOfNotes.get(i)+1);
        this.foundsDispensed += i;
    }

    public Integer getFoundsDispensed() {
        return foundsDispensed;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getAtmMachineId() {
        return atmMachineId;
    }

    public void setFoundsDispensed(Integer foundsDispensed) {
        this.foundsDispensed = foundsDispensed;
    }

    public void setNumberOfNotes(HashMap<Integer, Integer> numberOfNotes) {
        this.numberOfNotes = numberOfNotes;
    }

    public HashMap<Integer, Integer> getNumberOfNotes() {
        return numberOfNotes;
    }


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public void setAtmMachineId(Integer atmMachineId) {
        this.atmMachineId = atmMachineId;
    }

    @Override
    public String toString() {
        return "The customer with Id " +
                + customerId +
                " retired " + foundsDispensed + "$"+
                " with the following numberOfNotes=" + numberOfNotes;
    }
}
