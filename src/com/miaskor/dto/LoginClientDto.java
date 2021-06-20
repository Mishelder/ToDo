package com.miaskor.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginClientDto {
    String login;
    String password;
}
