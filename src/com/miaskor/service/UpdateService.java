package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.UpdateTaskDoneDto;
import com.miaskor.dto.UpdateTaskDto;
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
        taskDao.updateTaskDone(updateTaskDoneDtoToTaskMapper.map(updateTaskDoneDto));
    }

    public void updateTask(UpdateTaskDto updateTaskDto){
        taskDao.update(updateTaskDtoToTaskMapper.map(updateTaskDto));
    }

    public static UpdateService getInstance(){
        return INSTANCE;
    }
}
