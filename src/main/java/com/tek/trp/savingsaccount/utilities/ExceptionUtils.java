package com.tek.trp.savingsaccount.utilities;

public class ExceptionUtils {

    public static final String CUST_NOT_FOUND = "Customer Not Found in Database!";
    public static final String PAYEE_NOT_FOUND = "Payee Not Found in Database!";
    public static final String PAYEE_NOT_ACTIVE = "Payee Inactive! Activate Payee First.";
    public static final String TRANS_NOT_FOUND = "Transaction Not Found in Database!";
    public static final String LOW_BAL = " Transaction Can't Be completed! Balance less than Rs ";
    public static final String INVALID_TRANS_TYPE = "Transaction Can't Be completed! Wrong Transaction Type.";
    public static final String TRANS_NOT_FOUND_BY_CID = "There is no Transaction associated with your Customer Id ";

    private ExceptionUtils() {
    }
}
