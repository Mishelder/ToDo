package com.miaskor.validator;

import lombok.Value;

@Value
public class ErrorMessage {
    String code;
    String message;
}
