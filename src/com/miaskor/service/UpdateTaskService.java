package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.UpdateTaskDto;
import com.miaskor.mapper.UpdateTaskDtoToTaskMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateTaskService {
    private static final UpdateTaskService INSTANCE = new UpdateTaskService();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance(false);
    private final UpdateTaskDtoToTaskMapper updateTaskDtoToTaskMapper =
            UpdateTaskDtoToTaskMapper.getInstance();


    public void updateTask(UpdateTaskDto updateTaskDto){
        var mappedTask = updateTaskDtoToTaskMapper.map(updateTaskDto);
        taskDao.update(mappedTask);
    }

    public static UpdateTaskService getInstance(){
        return INSTANCE;
    }
}
