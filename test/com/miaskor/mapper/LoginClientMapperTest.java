package com.miaskor.mapper;

import com.miaskor.dto.LoginClientDto;
import com.miaskor.entity.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginClientMapperTest {

    private final LoginClientMapper loginClientMapper = LoginClientMapper.getInstance();

    @Test
    void map() {
        //given
        LoginClientDto giveLoginClientDto = LoginClientDto.builder()
                .login("example")
                .password("example_password!")
                .build();
        Client expected = Client.builder()
                .login("example")
                .password("example_password!")
                .build();
        //when
        var actual = loginClientMapper.map(giveLoginClientDto);
        //then
        assertAll(
                ()->assertEquals(expected.getLogin(),actual.getLogin()),
                ()->assertEquals(expected.getPassword(),actual.getPassword())
        );
    }
}