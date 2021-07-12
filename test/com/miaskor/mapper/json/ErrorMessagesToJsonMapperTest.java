package com.miaskor.mapper.json;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ErrorMessagesToJsonMapperTest {

    private final ErrorMessagesToJsonMapper jsonMapper = ErrorMessagesToJsonMapper.getInstance();

    @Test
    void map() {
        //given
        String expected = """
                {"example_value_01":"1","example_value_02":"2"}""";
        Map<String,String> map = new HashMap<>();
        map.put("example_value_01","1");
        map.put("example_value_02","2");
        //when
        var actual = jsonMapper.map(map);
        //then
        assertEquals(expected,actual);
    }
}