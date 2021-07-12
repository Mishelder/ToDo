package com.miaskor.mapper.json;

import com.miaskor.dto.UpdateTaskDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToUpdateTaskDtoMapperTest {

    private final JsonToUpdateTaskDtoMapper jsonMapper = JsonToUpdateTaskDtoMapper.getInstance();

    @Test
    void map() {
        //given
        String json = """
                {"id":1,"task":true}""";
        UpdateTaskDto expected = UpdateTaskDto.builder()
                .task("true")
                .id(1)
                .build();
        //when
        var actual = jsonMapper.map(json);
        //then
        assertAll(
                ()->assertEquals(expected.getId(),actual.getId()),
                ()->assertEquals(expected.getTask(),actual.getTask())
        );
    }
}