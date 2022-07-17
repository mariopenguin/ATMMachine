package com.example.ATM.Exceptions;

public class IncorrectPinException extends Exception{
    public IncorrectPinException(){
        super("Incorrect Pin!");
    }
}
