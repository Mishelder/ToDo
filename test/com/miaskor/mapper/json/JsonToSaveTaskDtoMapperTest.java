package com.miaskor.mapper.json;

import com.miaskor.dto.SaveTaskDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonToSaveTaskDtoMapperTest {

    private final JsonToSaveTaskDtoMapper jsonMapper = JsonToSaveTaskDtoMapper.getInstance();

    @Test
    void map() {
        //given
        String json = """
                {"task":"buy some coffee","doneTask":"checked","date":"2021-06-20"}""";
        SaveTaskDto expected = SaveTaskDto.builder()
                .task("buy some coffee")
                .doneTask("checked")
                .date("2021-06-20")
                .build();
        //when
        var actual = jsonMapper.map(json);
        //then
        assertAll(
                ()->assertEquals(expected.getTask(),actual.getTask()),
                ()->assertEquals(expected.getDoneTask(),actual.getDoneTask()),
                ()->assertEquals(expected.getDate(),actual.getDate())
        );
    }
}