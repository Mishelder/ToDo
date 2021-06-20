package com.miaskor.validator;

import lombok.Getter;


import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    @Getter
    private final Map<String,String> errorMessages = new HashMap<>();

    public void add(ErrorMessage errorMessage){
        this.errorMessages.put(errorMessage.getCode(),errorMessage.getMessage());
    }

    public boolean isValid(){
       return errorMessages.isEmpty();
    }
}
