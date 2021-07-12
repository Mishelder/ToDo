package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Task;
import com.miaskor.mapper.SaveTaskDtoToTaskMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTaskService {
    private static final SaveTaskService INSTANCE = new SaveTaskService();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance(false);
    private final SaveTaskDtoToTaskMapper mapper = SaveTaskDtoToTaskMapper.getInstance();

    public Task saveTask(SaveTaskDto saveTaskDto){
        var mappedTask = mapper.map(saveTaskDto);
        return taskDao.create(mappedTask);
    }

    public static SaveTaskService getInstance(){
        return INSTANCE;
    }
}
