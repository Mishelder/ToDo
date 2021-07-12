package com.miaskor.mapper.json;

import com.miaskor.dto.DeleteTaskDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToDeleteTaskDtoMapperTest {

    private final JsonToDeleteTaskDtoMapper jsonMapper = JsonToDeleteTaskDtoMapper.getInstance();

    @Test
    void map() {
        //given
        String json = """
                {"id":2}""";
        var expected = DeleteTaskDto.builder()
                .id(2)
                .build();
        //when
        var actual = jsonMapper.map(json);
        //then
        assertEquals(expected.getId(),actual.getId());
    }
}