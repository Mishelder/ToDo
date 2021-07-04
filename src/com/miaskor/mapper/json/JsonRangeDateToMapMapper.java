package com.miaskor.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaskor.mapper.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonRangeDateToMapMapper implements Mapper<String, Map<String, String>> {
    private static final JsonRangeDateToMapMapper INSTANCE = new JsonRangeDateToMapMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public Map<String, String> map(String object) {
        return jsonMapper.readValue(object, Map.class);
    }

    public static JsonRangeDateToMapMapper getInstance(){
        return INSTANCE;
    }

}
