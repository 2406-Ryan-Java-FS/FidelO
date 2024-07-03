package com.revature.item_management_application.exceptions;


public class DuplicateAccountException extends RuntimeException{
    public DuplicateAccountException(String message) {
        super(message);
    }
}
