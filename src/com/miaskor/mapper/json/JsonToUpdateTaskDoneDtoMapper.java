package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.UpdateTaskDoneDto;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonToUpdateTaskDoneDtoMapper implements Mapper<String, UpdateTaskDoneDto> {
    private static final JsonToUpdateTaskDoneDtoMapper INSTANCE = new JsonToUpdateTaskDoneDtoMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public UpdateTaskDoneDto map(String object) {
        return jsonMapper.readValue(object,UpdateTaskDoneDto.class);
    }

    public static JsonToUpdateTaskDoneDtoMapper getInstance(){
        return INSTANCE;
    }

}
