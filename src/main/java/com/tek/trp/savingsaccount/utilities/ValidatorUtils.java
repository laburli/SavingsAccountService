package com.tek.trp.savingsaccount.utilities;

public class ValidatorUtils {

    public static final String PATTERN_IFSC = "^[A-Za-z]{4}0[A-Z0-9a-z]{6}$";
    public static final String PATTERN_CUST_ID = "^\\d{7}$";
    public static final String PATTERN_ACC_NUM = "^\\d{9}$";
    public static final String PATTERN_TRANS_TYPE = "^([Cc]redit|[Dd]ebit)$";

    private ValidatorUtils() {
    }
}
