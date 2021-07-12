package com.miaskor.mapper.json;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonRangeDateToMapMapperTest {

    private final JsonRangeDateToMapMapper jsonMapper = JsonRangeDateToMapMapper.getInstance();

    @Test
    void map() {
        //given
        String json = """
                {"example_value_01":"1","example_value_02":"2"}""";
        Map<String, String> expected = new HashMap<>();
        expected.put("example_value_01", "1");
        expected.put("example_value_02", "2");
        //when
        var actual = jsonMapper.map(json);
        //then
        assertAll(
                () -> assertEquals(expected.get("example_value_01"), actual.get("example_value_01")),
                () -> assertEquals(expected.get("example_value_02"), actual.get("example_value_02"))
        );
    }
}