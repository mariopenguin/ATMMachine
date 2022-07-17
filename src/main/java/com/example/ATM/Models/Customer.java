package com.example.ATM.Models;

import javax.persistence.*;

@Entity
@Table(name="Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    private String name;
    private Integer pin;
    private Integer currentFunds;
    private Integer maxWithdrawal;


    public Customer(String name, Integer pin, Integer currentFunds, Integer maxWithdrawal) {
        this.name = name;
        this.pin = pin;
        this.currentFunds = currentFunds;
        this.maxWithdrawal = maxWithdrawal;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void retireFunds(Integer money){
        this.currentFunds -= money;
    }

    public Integer getPin() {
        return pin;
    }

    public Integer getCurrentFunds() {
        return currentFunds;
    }

    public void setCurrentFunds(Integer currentFunds) {
        this.currentFunds = currentFunds;
    }

    public Integer getMaxWithdrawal() {
        return maxWithdrawal;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", pin=" + pin +
                ", currentFounds=" + currentFunds +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String showCurrentFunds() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", currentFounds=" + currentFunds +
                '}';
    }

    public Customer() {
        this.name = "John Doe";
        this.pin = 123;
        this.currentFunds = 100;
        this.maxWithdrawal= 500;
    }
}
