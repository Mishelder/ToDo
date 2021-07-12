package com.miaskor.mapper.json;

import com.miaskor.dto.LoginClientDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToLoginClientDtoMapperTest {

    private final JsonToLoginClientDtoMapper jsonMapper = JsonToLoginClientDtoMapper.getInstance();

    @Test
    void map() {
        //given
        String json = """
                {"login":"123","password":"qwe"}""";
        LoginClientDto expected = LoginClientDto.builder()
                .login("123")
                .password("qwe")
                .build();
        //when
        var actual = jsonMapper.map(json);
        //then
        assertAll(
                ()->assertEquals(expected.getLogin(),actual.getLogin()),
                ()->assertEquals(expected.getPassword(),actual.getPassword())
        );
    }
}