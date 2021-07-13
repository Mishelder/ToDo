package com.miaskor.mapper;

import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SaveTaskDtoToTaskMapperTest {

    private final SaveTaskDtoToTaskMapper saveTaskDtoToTaskMapper = SaveTaskDtoToTaskMapper.getInstance();

    @Test
    void map() {
        //given
        SaveTaskDto givenSaveTaskDto = SaveTaskDto.builder()
                .task("example task")
                .doneTask("checked")
                .clientId(1)
                .date("2021-12-01")
                .build();
        Task expected = Task.builder()
                .date(LocalDate.parse("2021-12-01"))
                .clientId(1)
                .done(true)
                .taskName("example task")
                .build();
        //when
        var actual = saveTaskDtoToTaskMapper.map(givenSaveTaskDto);
        //then
        assertAll(
                ()->assertEquals(expected.getClientId(),actual.getClientId()),
                ()->assertEquals(expected.getTaskName(),actual.getTaskName()),
                ()->assertEquals(expected.getDone(),actual.getDone()),
                ()->assertEquals(expected.getDate(),actual.getDate())
        );
    }
}