package com.miaskor.mapper;

import com.miaskor.dto.UpdateTaskDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateTaskDtoToTaskMapper implements Mapper<UpdateTaskDto, Task> {
    private static final UpdateTaskDtoToTaskMapper INSTANCE = new UpdateTaskDtoToTaskMapper();

    @Override
    public Task map(UpdateTaskDto object) {
        return Task.builder().id(object.getId())
                .taskName(object.getTask())
                .build();
    }

    public static UpdateTaskDtoToTaskMapper getInstance(){
        return INSTANCE;
    }
}
