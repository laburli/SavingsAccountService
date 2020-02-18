package com.tek.trp.savingsAccount.SavingsAccountService.Utilities;

public class ExceptionUtils {

    public static String exceptionToJsonConverter(int cid, String message) {
        return "{\n\"Id\" : \"" + cid + "\",\n\"Message\" : \"" + message + "\"\n}";
    }
}
