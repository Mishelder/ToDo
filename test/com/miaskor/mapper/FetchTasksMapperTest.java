package com.miaskor.mapper;

import com.miaskor.dto.FetchTaskDto;
import com.miaskor.entity.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FetchTasksMapperTest {

    private final FetchTasksMapper fetchTasksMapper = FetchTasksMapper.getInstance();

    @Test
    void map() {
        //given
        List<Task> givenListTasks = List.of(
                Task.builder()
                        .id(1)
                        .taskName("task first")
                        .done(true)
                        .date(LocalDate.parse("2021-03-12"))
                        .build(),
                Task.builder()
                        .id(3)
                        .taskName("task third")
                        .done(true)
                        .date(LocalDate.parse("2021-03-12"))
                        .build(),
                Task.builder()
                        .id(2)
                        .taskName("task second")
                        .done(false)
                        .date(LocalDate.parse("2021-03-11"))
                        .build()

        );
        List<FetchTaskDto> givenListFetchTaskDtos = List.of(
                FetchTaskDto.builder()
                        .id(1)
                        .taskName("task first")
                        .done("checked")
                        .build(),
                FetchTaskDto.builder()
                        .id(3)
                        .taskName("task third")
                        .done("checked")
                        .build(),
                FetchTaskDto.builder()
                        .id(2)
                        .taskName("task second")
                        .done("")
                        .build()
        );
        Map<LocalDate,List<Task>> givenMap = new HashMap<>();
        givenMap.put(LocalDate.parse("2021-03-12"),givenListTasks.subList(0,2));
        givenMap.put(LocalDate.parse("2021-03-11"),givenListTasks.subList(2, givenListTasks.size()));
        Map<String,List<FetchTaskDto>> expected = new HashMap<>();

        expected.put("2021-03-12",givenListFetchTaskDtos.subList(0,2));
        expected.put("2021-03-11",givenListFetchTaskDtos.subList(2, givenListTasks.size()));
        //when
        var actual = fetchTasksMapper.map(givenMap);
        //then
        assertAll(
                ()->assertEquals(expected.get("2021-03-12").get(0).getId(),actual.get("2021-03-12").get(0).getId()),
                ()->assertEquals(expected.get("2021-03-12").get(0).getTaskName(),actual.get("2021-03-12").get(0).getTaskName()),
                ()->assertEquals(expected.get("2021-03-12").get(0).getDone(),actual.get("2021-03-12").get(0).getDone()),

                ()->assertEquals(expected.get("2021-03-12").get(1).getId(),actual.get("2021-03-12").get(1).getId()),
                ()->assertEquals(expected.get("2021-03-12").get(1).getTaskName(),actual.get("2021-03-12").get(1).getTaskName()),
                ()->assertEquals(expected.get("2021-03-12").get(1).getDone(),actual.get("2021-03-12").get(1).getDone()),

                ()->assertEquals(expected.get("2021-03-11").get(0).getId(),actual.get("2021-03-11").get(0).getId()),
                ()->assertEquals(expected.get("2021-03-11").get(0).getTaskName(),actual.get("2021-03-11").get(0).getTaskName()),
                ()->assertEquals(expected.get("2021-03-11").get(0).getDone(),actual.get("2021-03-11").get(0).getDone())
        );
    }
}