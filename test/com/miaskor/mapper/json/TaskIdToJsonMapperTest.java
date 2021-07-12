package com.miaskor.mapper.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskIdToJsonMapperTest {

    private final TaskIdToJsonMapper jsonMapper = TaskIdToJsonMapper.getInstance();

    @Test
    void map() {
        //given
        Integer id = 3;
        String expected = """
                3""";
        //when
        var actual = jsonMapper.map(id);
        //then
        assertEquals(expected,actual);
    }
}