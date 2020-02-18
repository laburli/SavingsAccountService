package com.tek.trp.savingsAccount.SavingsAccountService.Utilities;

import java.util.regex.Pattern;

public class ValidatorUtils {

    String patternIFSC = "^[A-Za-z]{4}0[A-Z0-9a-z]{6}$";
    String dateTime = "2018-05-05T11:50:55";

    public boolean isValidIFSC(String ifsc) {
        return Pattern.matches(patternIFSC, ifsc);
    }

}
