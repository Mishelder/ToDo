package com.miaskor.mapper;

import com.miaskor.dto.LoginClientDto;
import com.miaskor.entity.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginClientMapper implements Mapper<LoginClientDto, Client>{

    private static final LoginClientMapper INSTANCE = new LoginClientMapper();

    @Override
    public Client map(LoginClientDto object) {
        return Client.builder()
                .login(object.getLogin())
                .password(object.getPassword())
                .build();
    }

    public static LoginClientMapper getInstance(){
        return INSTANCE;
    }
}
