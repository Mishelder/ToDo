package com.miaskor.mapper.json;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.UpdateClientDto;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonToUpdateClientDtoMapper implements Mapper<String, UpdateClientDto> {
    private static final JsonToUpdateClientDtoMapper INSTANCE = new JsonToUpdateClientDtoMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public UpdateClientDto map(String from) {
        return objectMapper.readValue(from, UpdateClientDto.class);
    }

    public static JsonToUpdateClientDtoMapper getInstance() {
        return INSTANCE;
    }
}
