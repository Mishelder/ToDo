package com.miaskor.mapper;

import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.miaskor.util.DateUtil.FORMATTER_FOR_POSTGRESQL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTaskDtoToTaskMapper implements Mapper<SaveTaskDto, Task> {
    private static final SaveTaskDtoToTaskMapper INSTANCE = new SaveTaskDtoToTaskMapper();

    @Override
    public Task map(SaveTaskDto from) {
        return Task.builder()
                .clientId(from.getClientId())
                .done(from.getDoneTask().equals("checked"))
                .taskName(from.getTask())
                .date(LocalDate.parse(from.getDate(),FORMATTER_FOR_POSTGRESQL))
                .build();
    }

    public static SaveTaskDtoToTaskMapper getInstance(){
        return INSTANCE;
    }
}
