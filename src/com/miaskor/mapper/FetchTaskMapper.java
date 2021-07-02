package com.miaskor.mapper;

import com.miaskor.dto.FetchTaskDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FetchTaskMapper implements Mapper<List<Task>,List<FetchTaskDto>> {
    private static final FetchTaskMapper INSTANCE = new FetchTaskMapper();

    public List<FetchTaskDto> map(List<Task> from) {
        return from.stream()
                .map(task ->
                        FetchTaskDto.builder()
                                .taskName(task.getTaskName())
                                .done(task.getDone()?"checked":"")
                                .build())
                .collect(Collectors.toList());
    }

    public static FetchTaskMapper getInstance() {
        return INSTANCE;
    }
}
