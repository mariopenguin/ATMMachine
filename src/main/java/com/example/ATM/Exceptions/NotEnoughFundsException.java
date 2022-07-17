package com.example.ATM.Exceptions;

public class NotEnoughFundsException extends Exception{
    public NotEnoughFundsException(String message){
        super(message);
    }
}
