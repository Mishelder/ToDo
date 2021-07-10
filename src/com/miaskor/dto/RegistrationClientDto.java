package com.miaskor.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationClientDto {
    String login;
    String email;
    String password;
}
