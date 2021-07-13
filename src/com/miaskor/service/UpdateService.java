package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.UpdateTaskDto;
import com.miaskor.mapper.UpdateTaskDtoToTaskMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateService {
    private static final UpdateService INSTANCE = new UpdateService();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance(false);
    private final UpdateTaskDtoToTaskMapper updateTaskDtoToTaskMapper =
            UpdateTaskDtoToTaskMapper.getInstance();


    public void updateTask(UpdateTaskDto updateTaskDto){
        var mappedTask = updateTaskDtoToTaskMapper.map(updateTaskDto);
        taskDao.update(mappedTask);
    }

    public static UpdateService getInstance(){
        return INSTANCE;
    }
}
