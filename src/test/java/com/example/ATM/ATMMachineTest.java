package com.example.ATM;

import com.example.ATM.Exceptions.NotEnoughFundsException;
import com.example.ATM.Exceptions.NotEnoughtBillsException;
import com.example.ATM.Models.ATMMachine;
import com.example.ATM.Models.FundsDispension;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ATMMachineTest {
    ATMMachine atmMachine;
    @BeforeEach
    public void setUpATM(){
        HashMap<Integer,Integer> bills = new HashMap<Integer,Integer>();
        bills.put(50,10);
        bills.put(20,30);
        bills.put(10,30);
        bills.put(5,20);
        this.atmMachine = new ATMMachine(123,1500,500,bills);
    }

    @Test
    public void dispenseMoneyBillsTest() {
        setUpATM();
        Exception exception = assertThrows(NotEnoughFundsException.class, () -> {
            atmMachine.dispenseMoneyBills(33);
        });
        assertEquals("The desired amount cannot be withdrawn",exception.getMessage());
    }

    @Test
    public void dispenseMoneyBillsTest123() throws NotEnoughFundsException, NotEnoughtBillsException {
        setUpATM();
        FundsDispension f =  atmMachine.dispenseMoney(50);
        assertEquals(f.getFoundsDispensed()+"",""+50);
    }

    @Test
    public void dispenseMoneyBillsTest1() throws NotEnoughtBillsException, NotEnoughFundsException {
        setUpATM();

        assertEquals((int) 150,(long) atmMachine.dispenseMoneyBills(150).getFoundsDispensed());

    }
    @Test
    public void dispenseMoneyBillsTest2() throws NotEnoughtBillsException, NotEnoughFundsException {
        setUpATM();
        assertEquals((int) 370,(long) atmMachine.dispenseMoneyBills(370).getFoundsDispensed());
    }
    @Test
    public void dispenseMoneyBillsTest4() throws NotEnoughtBillsException, NotEnoughFundsException {
        setUpATM();
        Exception exception = assertThrows(NotEnoughFundsException.class, () -> {
            atmMachine.dispenseMoneyBills(373);
        });
        assertEquals("The desired amount cannot be withdrawn",exception.getMessage());
    }
    //Let's see
    @Test
    public void dispenseMoneyBillsTest3() {
        setUpATM();
        Exception exception = assertThrows(NotEnoughFundsException.class, () -> {
            atmMachine.dispenseMoneyBills(33);
        });
        assertEquals("The desired amount cannot be withdrawn",exception.getMessage());
    }

    //The cashier has run out of bills
    @Test
    public void dispenseMoneyBillsTest5() {
        setUpATM();
        Exception exception = assertThrows(NotEnoughtBillsException.class, () -> {
            for (int i = 0; i < 500; i++)
                atmMachine.dispenseMoneyBills(5);
        });
        assertEquals("Cannot remove more bills than the cashier has",exception.getMessage());
    }

    @Test
    public void dispenseMoneyBillsTest6() throws NotEnoughtBillsException, NotEnoughFundsException {
        setUpATM();
        for (int i = 0; i < 15; i++)
            atmMachine.dispenseMoneyBills(5);

        assertEquals((int) 75,1500-atmMachine.getCurrentFounds());
    }
}
