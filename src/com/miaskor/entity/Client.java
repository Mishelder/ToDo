package com.miaskor.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private Integer id;
    private String login;
    private String email;
    private String password;
}
