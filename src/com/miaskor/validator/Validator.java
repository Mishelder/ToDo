package com.miaskor.validator;

public interface Validator <T>{
    ValidationResult isValid(T object);
}
