package com.miaskor.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginClientDto {
    String login;
    String password;
}
