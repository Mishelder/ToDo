package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.FetchTaskDto;
import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Task;
import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.FetchTasksSaveTasksMapper;
import com.miaskor.mapper.SaveTasksMapper;
import com.miaskor.validator.SaveTaskValidator;
import com.miaskor.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class SaveTaskService {

    private static final SaveTaskService INSTANCE = new SaveTaskService();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance();
    private final SaveTaskValidator validator = SaveTaskValidator.getInstance();
    private final SaveTasksMapper mapper = SaveTasksMapper.getInstance();
    private final FetchTasksSaveTasksMapper fetchTasksSaveTasksMapper = FetchTasksSaveTasksMapper.getInstance();
    private final DeleteTaskService deleteTaskService = DeleteTaskService.getInstance();

    public List<FetchTaskDto> saveTask(List<SaveTaskDto> tasksDto,Integer clientId,LocalDate date){
        ValidationResult validationResult = validator.isValid(tasksDto);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrorMessages());
        }
        List<Task> tasks = mapper.map(tasksDto);
        deleteTaskService.deleteTasksByDayAndClientId(clientId, date);
        if(!tasks.isEmpty())
        taskDao.createTasks(tasks);
        return tasks.isEmpty() ? null : fetchTasksSaveTasksMapper.map(tasksDto);
    }



    public static SaveTaskService getInstance(){
        return INSTANCE;
    }
}
