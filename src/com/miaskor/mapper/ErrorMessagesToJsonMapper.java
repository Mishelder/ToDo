package com.miaskor.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public String map(Map<String, String> object)  {
        return objectMapper.writeValueAsString(object);
    }

    public static ErrorMessagesToJsonMapper getInstance(){
        return INSTANCE;
    }
}
