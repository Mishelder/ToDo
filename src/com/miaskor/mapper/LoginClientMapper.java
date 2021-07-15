package com.miaskor.mapper;

import com.miaskor.dto.LoginClientDto;
import com.miaskor.entity.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Deprecated
public class LoginClientMapper implements Mapper<LoginClientDto, Client>{

    private static final LoginClientMapper INSTANCE = new LoginClientMapper();

    @Override
    public Client map(LoginClientDto from) {
        return Client.builder()
                .login(from.getLogin())
                .password(from.getPassword())
                .build();
    }

    public static LoginClientMapper getInstance(){
        return INSTANCE;
    }
}
