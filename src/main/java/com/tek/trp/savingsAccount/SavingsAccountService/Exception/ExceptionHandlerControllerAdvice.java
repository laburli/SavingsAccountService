package com.tek.trp.savingsAccount.SavingsAccountService.Exception;

import com.tek.trp.savingsAccount.SavingsAccountService.Customer.CustNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(value = PayeeNotFoundException.class)
    public ResponseEntity<Object> exceptionPNFE(PayeeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TransactionNotFoundException.class)
    public ResponseEntity<Object> exceptionTNFE(TransactionNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IncompleteTransactionException.class)
    public ResponseEntity<Object> exceptionITE(IncompleteTransactionException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = CustNotFoundException.class)
    public ResponseEntity<Object> exceptionCNFE(CustNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleDateTimeException(final DateTimeParseException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        //error.setErrorMessage("Wrong Date Format! Please Try Again! ");
    }

}
