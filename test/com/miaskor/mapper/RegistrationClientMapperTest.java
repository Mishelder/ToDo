package com.miaskor.mapper;

import com.miaskor.dto.LoginClientDto;
import com.miaskor.dto.RegistrationClientDto;
import com.miaskor.entity.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationClientMapperTest {

    private final RegistrationClientMapper registrationClientMapper = RegistrationClientMapper.getInstance();

    @Test
    void map() {
        //given
        RegistrationClientDto giveLoginClientDto = RegistrationClientDto.builder()
                .login("example")
                .email("example@mail.com")
                .password("example_password!")
                .build();
        Client expected = Client.builder()
                .login("example")
                .email("example@mail.com")
                .password("example_password!")
                .build();
        //when
        var actual = registrationClientMapper.map(giveLoginClientDto);
        //then
        assertAll(
                ()->assertEquals(expected.getLogin(),actual.getLogin()),
                ()->assertEquals(expected.getPassword(),actual.getPassword()),
                ()->assertEquals(expected.getEmail(),actual.getEmail())
        );
    }
}