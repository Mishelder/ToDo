package com.miaskor.mapper.json;

import com.miaskor.dto.LoginClientDto;
import com.miaskor.dto.RegistrationClientDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToRegistrationClientDtoMapperTest {

    private final JsonToRegistrationClientDtoMapper jsonMapper = JsonToRegistrationClientDtoMapper.getInstance();

    @Test
    void map() {
        //given
        String json = """
                {"login":"123","password":"qwe","email":"example@mail.com"}""";
        RegistrationClientDto expected = RegistrationClientDto.builder()
                .login("123")
                .password("qwe")
                .email("example@mail.com")
                .build();
        //when
        var actual = jsonMapper.map(json);
        //then
        assertAll(
                ()->assertEquals(expected.getLogin(),actual.getLogin()),
                ()->assertEquals(expected.getPassword(),actual.getPassword()),
                ()->assertEquals(expected.getEmail(),actual.getEmail())
        );
    }
}