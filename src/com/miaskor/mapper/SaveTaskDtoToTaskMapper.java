package com.miaskor.mapper;

import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTaskDtoToTaskMapper implements Mapper<SaveTaskDto, Task> {
    private static final SaveTaskDtoToTaskMapper INSTANCE = new SaveTaskDtoToTaskMapper();

    @Override
    public Task map(SaveTaskDto object) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Task.builder()
                .clientId(object.getClientId())
                .done(object.getDoneTask().equals("checked"))
                .taskName(object.getTask())
                .date(LocalDate.parse(object.getDate(),dateTimeFormatter))
                .build();
    }

    public static SaveTaskDtoToTaskMapper getInstance(){
        return INSTANCE;
    }
}
