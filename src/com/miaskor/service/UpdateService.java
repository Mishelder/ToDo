package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.UpdateTaskDoneDto;
import com.miaskor.dto.UpdateTaskDto;
import com.miaskor.entity.Task;
import com.miaskor.mapper.UpdateTaskDoneDtoToTaskMapper;
import com.miaskor.mapper.UpdateTaskDtoToTaskMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateService {
    private static final UpdateService INSTANCE = new UpdateService();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance();
    private final UpdateTaskDoneDtoToTaskMapper updateTaskDoneDtoToTaskMapper =
            UpdateTaskDoneDtoToTaskMapper.getInstance();
    private final UpdateTaskDtoToTaskMapper updateTaskDtoToTaskMapper =
            UpdateTaskDtoToTaskMapper.getInstance();

    public void updateDoneTask(UpdateTaskDoneDto updateTaskDoneDto){
        var mappedTask = updateTaskDoneDtoToTaskMapper.map(updateTaskDoneDto);
        taskDao.updateTaskDone(mappedTask);
    }

    public void updateTask(UpdateTaskDto updateTaskDto){
        var mappedTask = updateTaskDtoToTaskMapper.map(updateTaskDto);
        taskDao.update(mappedTask);
    }

    public static UpdateService getInstance(){
        return INSTANCE;
    }
}
