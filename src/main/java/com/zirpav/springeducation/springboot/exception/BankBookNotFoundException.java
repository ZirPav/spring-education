package com.zirpav.springeducation.springboot.exception;

public class BankBookNotFoundException extends RuntimeException{

    public BankBookNotFoundException() {
    }

    public BankBookNotFoundException(String message) {
        super(message);
    }

}
