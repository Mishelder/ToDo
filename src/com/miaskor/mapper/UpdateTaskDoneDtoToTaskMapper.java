package com.miaskor.mapper;

import com.miaskor.dto.UpdateTaskDoneDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateTaskDoneDtoToTaskMapper implements Mapper<UpdateTaskDoneDto, Task> {
    private static final UpdateTaskDoneDtoToTaskMapper INSTANCE = new UpdateTaskDoneDtoToTaskMapper();

    @Override
    public Task map(UpdateTaskDoneDto object) {
        return Task.builder().id(object.getId())
                .done(object.getIsDone())
                .build();
    }

    public static UpdateTaskDoneDtoToTaskMapper getInstance(){
        return INSTANCE;
    }
}
