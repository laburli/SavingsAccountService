package com.tek.trp.savingsaccount.utilities;

public class ValidatorUtils {

    public static final String patternIFSC = "^[A-Za-z]{4}0[A-Z0-9a-z]{6}$";
    public static final String patternCustomerId = "^\\d{7}$";
    public static final String patternAccNum = "^\\d{9}$";
    public static final String patternDate = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
}
