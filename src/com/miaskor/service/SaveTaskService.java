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

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class SaveTaskService {

    private static final SaveTaskService INSTANCE = new SaveTaskService();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance();
    private final SaveTaskValidator validator = SaveTaskValidator.getInstance();
    private final SaveTasksMapper mapper = SaveTasksMapper.getInstance();
    private final FetchTasksSaveTasksMapper fetchTasksSaveTasksMapper = FetchTasksSaveTasksMapper.getInstance();

    public List<FetchTaskDto> saveTask(List<SaveTaskDto> tasksDto){
        ValidationResult validationResult = validator.isValid(tasksDto);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrorMessages());
        }
        List<Task> tasks = mapper.map(tasksDto);
        taskDao.deleteTaskByDate(tasksDto.get(0).getDate().toLocalDate());
        if(!tasks.isEmpty())
        taskDao.createTasks(tasks);
        return tasks.isEmpty() ? emptyTasks() : fetchTasksSaveTasksMapper.map(tasksDto);
    }

    private List<FetchTaskDto> emptyTasks(){
        List<FetchTaskDto> tasks = new ArrayList<>();
        for(int i = 0;i<15;i++){
            tasks.add(FetchTaskDto.builder()
                    .indexInForm(-1)
                    .taskName("")
                    .done("")
                    .build());
        }
        return tasks;
    }

    public static SaveTaskService getInstance(){
        return INSTANCE;
    }
}
