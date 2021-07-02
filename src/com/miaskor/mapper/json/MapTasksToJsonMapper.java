package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.dto.FetchTaskDto;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapTasksToJsonMapper implements Mapper<Map<String, List<FetchTaskDto>>,String> {
    private static final MapTasksToJsonMapper INSTANCE = new MapTasksToJsonMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public String map(Map<String, List<FetchTaskDto>> object) {
        return jsonMapper.writeValueAsString(object);
    }

    public static MapTasksToJsonMapper getInstance(){
        return INSTANCE;
    }

}
