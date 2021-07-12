package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.entity.Task;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskIdToJsonMapper implements Mapper<Integer,String> {
    private static final TaskIdToJsonMapper INSTANCE = new TaskIdToJsonMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String map(Integer taskId) {
        return jsonMapper.writeValueAsString(taskId);
    }

    public static TaskIdToJsonMapper getInstance(){
        return INSTANCE;
    }
}
