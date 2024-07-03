package com.revature.item_management_application.exceptions;

public class InvalidTrainerException extends RuntimeException{
    public InvalidTrainerException(String message) {
        super(message);
    }
}