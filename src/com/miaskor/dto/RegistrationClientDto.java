package com.miaskor.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationClientDto {
    String login;
    String email;
    String password;
}
