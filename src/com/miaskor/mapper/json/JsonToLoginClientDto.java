package com.miaskor.mapper.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.LoginClientDto;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonToLoginClientDto implements Mapper<String, LoginClientDto> {
    private static final JsonToLoginClientDto INSTANCE = new JsonToLoginClientDto();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public LoginClientDto map(String object) {
        return objectMapper.readValue(object, LoginClientDto.class);
    }

    public static JsonToLoginClientDto getInstance() {
        return INSTANCE;
    }
}
