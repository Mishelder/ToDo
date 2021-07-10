package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessagesToJsonMapper implements Mapper<Map<String,String>,String> {
    private static final ErrorMessagesToJsonMapper INSTANCE = new ErrorMessagesToJsonMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public String map(Map<String, String> from)  {
        return objectMapper.writeValueAsString(from);
    }

    public static ErrorMessagesToJsonMapper getInstance(){
        return INSTANCE;
    }
}
