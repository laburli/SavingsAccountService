package com.tek.trp.savingsaccount.utilities;

import com.tek.trp.savingsaccount.exception.InvalidParameterException;

import java.util.regex.Pattern;

public class ValidatorUtils {

    String id = "Id";
    String patternIFSC = "^[A-Za-z]{4}0[A-Z0-9a-z]{6}$";
    String patternCustomerId = "^\\d{7}$";
    String patternDate = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    String dateTime = "2020-02-18 18:30:30.123000";

    public String isValidIFSC(String ifsc) throws InvalidParameterException {
         if(Pattern.matches(patternIFSC, ifsc))
             return ifsc;
         else
             throw new InvalidParameterException("IFSC Code");
    }

    public boolean isValidCustomerId(String customerId) {
        return Pattern.matches(patternCustomerId, customerId);
    }

    public boolean isValidAccNum(String accNum) {
        return Pattern.matches(patternCustomerId, accNum);
    }

    public boolean isValidDateFormat(String date) {
        return Pattern.matches(patternDate, date);
    }


}
