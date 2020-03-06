package com.tek.trp.savingsaccount.exception;

public class PayeeNotFoundException extends RuntimeException {

    public PayeeNotFoundException(int pid, String message) {
        super("{\n\"PayeeID \" : \"" + pid + "\",\n\"Message\" : \"" + message + "\"\n}");
    }
}
