package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.entity.Task;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskIdToJsonMapper implements Mapper<Task,String> {
    private static final TaskIdToJsonMapper INSTANCE = new TaskIdToJsonMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String map(Task object) {
        return jsonMapper.writeValueAsString(object.getId());
    }

    public static TaskIdToJsonMapper getInstance(){
        return INSTANCE;
    }
}
