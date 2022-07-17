package com.example.ATM.Models;

import com.example.ATM.Exceptions.NotEnoughFundsException;
import com.example.ATM.Exceptions.NotEnoughtBillsException;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="atm")
public class ATMMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountNumber;
    private Integer openingBalance;
    private Integer overdraft;
    private HashMap<Integer,Integer> bills;
    private Integer currentFounds;

    public ATMMachine(Integer accountNumber, Integer openingBalance, Integer overdraft, HashMap<Integer,Integer> bills) {
        this.accountNumber = accountNumber;
        this.openingBalance = openingBalance;
        this.overdraft = overdraft;
        this.bills = bills;
        this.currentFounds = 0;
        for (Integer i: bills.keySet())
            currentFounds+= bills.get(i)*i;
    }

    public ATMMachine() {
    }




    public FundsDispension dispenseMoney(Integer money) throws NotEnoughFundsException, NotEnoughtBillsException {
        if(this.currentFounds < money )
            throw new NotEnoughFundsException("The ATM machine does not have enough money");
         FundsDispension foundsDispension = dispenseMoneyBills(money);

        return foundsDispension;
    }

    public FundsDispension dispenseMoneyBills(Integer money) throws NotEnoughFundsException, NotEnoughtBillsException {
        //Get the types of money bills that we have and sort them for the greedy algorithm
        List<Integer> moneyBillsValues = new ArrayList<Integer>();
        FundsDispension foundsDispension = new FundsDispension();
        for (EMoneyBills i: EMoneyBills.values())
            moneyBillsValues.add(i.getValue());
        Collections.sort(moneyBillsValues, Collections.reverseOrder());

        //See if we can retrieve the desired money with the bills that has the ATMMachine
        HashMap<Integer, Integer> currentBills = (HashMap<Integer, Integer>) this.bills.clone();
        Iterator it = moneyBillsValues.iterator();
        int j = (int) it.next();

        while(money>0 && it != null){
            if((money < j || foundsDispension.getNumberOfNotes().get(j)>this.bills.get(j)) && it.hasNext())
                j= (int) it.next();
            else{
                money-=j;
                foundsDispension.addNewBill(j);
                if (money<0)
                    throw new NotEnoughFundsException("The desired amount cannot be withdrawn");
                if (currentBills.get(j)<0)
                    throw new NotEnoughtBillsException("The cashier does not have enough bills");
            }
        }
        //Successful operation, remove founds from ATMMachine.
        removeFounds(foundsDispension);
        return foundsDispension;
    }

    public void removeFounds(FundsDispension foundsDispension) throws NotEnoughtBillsException {
        HashMap<Integer,Integer> removedBills = foundsDispension.getNumberOfNotes();
        for (Integer e: foundsDispension.getNumberOfNotes().keySet()) {
            if(this.bills.get(e)<removedBills.get(e))
                throw new NotEnoughtBillsException("Cannot remove more bills than the cashier has");
            this.bills.put(e,bills.get(e)-removedBills.get(e));
            this.currentFounds-=removedBills.get(e)*e;
        }
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Integer openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Integer getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(Integer overdraft) {
        this.overdraft = overdraft;
    }

    public HashMap<Integer, Integer> getBills() {
        return bills;
    }

    public void setBills(HashMap<Integer, Integer> bills) {
        this.bills = bills;
    }

    public void setCurrentFounds(Integer currentFounds) {
        this.currentFounds = currentFounds;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Integer getCurrentFounds() {
        return currentFounds;
    }

    @Override
    public String toString() {
        return "ATMMachine{" +
                "accountNumber=" + accountNumber +
                ", openingBalance=" + openingBalance +
                ", overdraft=" + overdraft +
                ", currentFounds=" + currentFounds +
                ", bills=" + bills +
                '}';
    }
}
