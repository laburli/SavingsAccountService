package com.tek.trp.savingsaccount.exception;

public class InvalidParameterException extends Exception{
    public InvalidParameterException(String parameter) {
        super("\"Message\" : \" Invalid Parameter" + parameter + "\"\n}");
    }
}
