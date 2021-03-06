package com.tek.trp.savingsaccount.exception;

public class IncompleteTransactionException extends RuntimeException {
    public IncompleteTransactionException(String cid, String message) {
        super("{\n\"CustomerID \" : \"" + cid + "\",\n\"Message\" : \"" + message + "\"\n}");
    }
}
