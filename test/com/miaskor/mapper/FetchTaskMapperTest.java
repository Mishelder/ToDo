package com.miaskor.mapper;

import com.miaskor.dto.FetchTaskDto;
import com.miaskor.entity.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FetchTaskMapperTest {

    private final FetchTaskMapper fetchTaskMapper = FetchTaskMapper.getInstance();

    @Test
    void map() {
        //given
        List<Task> givenList = List.of(
                Task.builder()
                        .id(1)
                        .taskName("task first")
                        .done(true)
                        .date(LocalDate.parse("2021-03-12"))
                        .build(),
                Task.builder()
                        .id(2)
                        .taskName("task second")
                        .done(false)
                        .date(LocalDate.parse("2021-03-11"))
                        .build(),
                Task.builder()
                        .id(3)
                        .taskName("task third")
                        .done(true)
                        .date(LocalDate.parse("2021-03-12"))
                        .build()
        );
        List<FetchTaskDto> expected = List.of(
                FetchTaskDto.builder()
                        .id(1)
                        .taskName("task first")
                        .done("checked")
                        .build(),
                FetchTaskDto.builder()
                        .id(2)
                        .taskName("task second")
                        .done("")
                        .build(),
                FetchTaskDto.builder()
                        .id(3)
                        .taskName("task third")
                        .done("checked")
                        .build()
        );
        //when
        var actual = fetchTaskMapper.map(givenList);
        //then
        assertAll(
                () -> assertEquals(expected.get(0).getId(), actual.get(0).getId()),
                () -> assertEquals(expected.get(0).getDone(), actual.get(0).getDone()),
                () -> assertEquals(expected.get(0).getTaskName(), actual.get(0).getTaskName()),

                () -> assertEquals(expected.get(1).getId(), actual.get(1).getId()),
                () -> assertEquals(expected.get(1).getDone(), actual.get(1).getDone()),
                () -> assertEquals(expected.get(1).getTaskName(), actual.get(1).getTaskName()),

                () -> assertEquals(expected.get(2).getId(), actual.get(2).getId()),
                () -> assertEquals(expected.get(2).getDone(), actual.get(2).getDone()),
                () -> assertEquals(expected.get(2).getTaskName(), actual.get(2).getTaskName())
        );
    }
}