package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.SaveTaskDto;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonToSaveTaskDtoMapper implements Mapper<String, SaveTaskDto> {
    private static final JsonToSaveTaskDtoMapper INSTANCE = new JsonToSaveTaskDtoMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public SaveTaskDto map(String from) {
        return jsonMapper.readValue(from,SaveTaskDto.class);
    }

    public static JsonToSaveTaskDtoMapper getInstance(){
        return INSTANCE;
    }

}
