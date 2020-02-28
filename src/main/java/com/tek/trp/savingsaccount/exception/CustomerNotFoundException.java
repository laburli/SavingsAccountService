package com.tek.trp.savingsaccount.exception;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String cid, String message) {
        super("{\n\"CustomerID \" : \"" + cid + "\",\n\"Message\" : \"" + message + "\"\n}");
    }
}
