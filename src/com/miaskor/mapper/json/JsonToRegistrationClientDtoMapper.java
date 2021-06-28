package com.miaskor.mapper.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.RegistrationClientDto;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonToRegistrationClientDtoMapper implements Mapper<String, RegistrationClientDto> {
    private static final JsonToRegistrationClientDtoMapper INSTANCE = new JsonToRegistrationClientDtoMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public RegistrationClientDto map(String json) {
        return jsonMapper.readValue(json, RegistrationClientDto.class);
    }

    public static JsonToRegistrationClientDtoMapper getInstance() {
        return INSTANCE;
    }
}
