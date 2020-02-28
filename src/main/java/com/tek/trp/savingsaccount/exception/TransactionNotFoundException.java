package com.tek.trp.savingsaccount.exception;

public class TransactionNotFoundException extends Exception {

    public TransactionNotFoundException(int tid, String message) {
        super("{\n\"TransactionID \" : \"" + tid + "\",\n\"Message\" : \"" + message + "\"\n}");
    }
}
