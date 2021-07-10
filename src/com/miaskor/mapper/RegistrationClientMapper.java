package com.miaskor.mapper;

import com.miaskor.dto.RegistrationClientDto;
import com.miaskor.entity.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationClientMapper implements Mapper<RegistrationClientDto, Client> {

    private static final RegistrationClientMapper INSTANCE = new RegistrationClientMapper();

    @Override
    public Client map(RegistrationClientDto from) {
        return Client.builder()
                .login(from.getLogin())
                .email(from.getEmail())
                .password(from.getPassword())
                .build();
    }

    public static RegistrationClientMapper getInstance(){
        return INSTANCE;
    }
}
