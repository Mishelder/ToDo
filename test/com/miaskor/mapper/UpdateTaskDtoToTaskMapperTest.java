package com.miaskor.mapper;

import com.miaskor.dto.UpdateTaskDto;
import com.miaskor.entity.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTaskDtoToTaskMapperTest {

    private final UpdateTaskDtoToTaskMapper updateTaskDtoToTaskMapper = UpdateTaskDtoToTaskMapper.getInstance();

    @Test
    void map() {
        //given
        UpdateTaskDto givenUpdateTaskDto = UpdateTaskDto.builder()
                .task("example task")
                .id(1)
                .isDone(true)
                .build();
        Task expected = Task.builder()
                .id(1)
                .taskName("example task")
                .done(true)
                .build();
        //when
        var actual = updateTaskDtoToTaskMapper.map(givenUpdateTaskDto);
        //then
        assertAll(
                ()->assertEquals(expected.getId(),actual.getId()),
                ()->assertEquals(expected.getDone(),actual.getDone()),
                ()->assertEquals(expected.getTaskName(),actual.getTaskName())
        );
    }
}