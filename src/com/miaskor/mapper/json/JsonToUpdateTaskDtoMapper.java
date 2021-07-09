package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.UpdateTaskDto;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonToUpdateTaskDtoMapper implements Mapper<String, UpdateTaskDto> {
    private static final JsonToUpdateTaskDtoMapper INSTANCE = new JsonToUpdateTaskDtoMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public UpdateTaskDto map(String object) {
        return jsonMapper.readValue(object,UpdateTaskDto.class);
    }

    public static JsonToUpdateTaskDtoMapper getInstance(){
        return INSTANCE;
    }

}
