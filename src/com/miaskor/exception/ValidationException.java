package com.miaskor.exception;

import lombok.Getter;

import java.util.Map;

public class ValidationException extends RuntimeException {

    @Getter
    private final Map<String,String> errorMessages;

    public ValidationException(Map<String,String> errorMessages){
        this.errorMessages = errorMessages;
    }

}
