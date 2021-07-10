package com.miaskor.mapper.json;

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
    public RegistrationClientDto map(String from) {
        return jsonMapper.readValue(from, RegistrationClientDto.class);
    }

    public static JsonToRegistrationClientDtoMapper getInstance() {
        return INSTANCE;
    }
}
