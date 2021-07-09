package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.DeleteTaskDto;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonToDeleteTaskDtoMapper implements Mapper<String, DeleteTaskDto> {
    private static final JsonToDeleteTaskDtoMapper INSTANCE = new JsonToDeleteTaskDtoMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public DeleteTaskDto map(String object) {
        return jsonMapper.readValue(object,DeleteTaskDto.class);
    }

    public static JsonToDeleteTaskDtoMapper getInstance(){
        return INSTANCE;
    }
}
