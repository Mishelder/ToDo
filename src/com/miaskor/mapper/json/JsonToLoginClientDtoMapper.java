package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.LoginClientDto;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonToLoginClientDtoMapper implements Mapper<String, LoginClientDto> {
    private static final JsonToLoginClientDtoMapper INSTANCE = new JsonToLoginClientDtoMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public LoginClientDto map(String object) {
        return objectMapper.readValue(object, LoginClientDto.class);
    }

    public static JsonToLoginClientDtoMapper getInstance() {
        return INSTANCE;
    }
}
