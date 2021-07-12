package com.miaskor.mapper.json;

import com.miaskor.dto.UpdateTaskDoneDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToUpdateTaskDoneDtoMapperTest {

    private final JsonToUpdateTaskDoneDtoMapper jsonMapper = JsonToUpdateTaskDoneDtoMapper.getInstance();

    @Test
    void map() {
        //given
        String json = """
                {"id":1,"isDone":true}""";
        UpdateTaskDoneDto expected = UpdateTaskDoneDto.builder()
                .isDone(true)
                .id(1)
                .build();
        //when
        var actual = jsonMapper.map(json);
        //then
        assertAll(
                ()->assertEquals(expected.getId(),actual.getId()),
                ()->assertEquals(expected.getIsDone(),actual.getIsDone())
        );
    }
}