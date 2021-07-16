package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Task;
import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.Mapper;
import com.miaskor.mapper.SaveTaskDtoToTaskMapper;
import com.miaskor.validator.SaveTaskValidator;
import com.miaskor.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.miaskor.util.DateUtil.FORMATTER_FOR_POSTGRESQL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTaskService {
    private static final SaveTaskService INSTANCE = new SaveTaskService();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance(false);
    private final SaveTaskDtoToTaskMapper mapper = SaveTaskDtoToTaskMapper.getInstance();
    private final SaveTaskValidator saveTaskValidator = SaveTaskValidator.getInstance();

    public Task saveTask(SaveTaskDto saveTaskDto) throws ValidationException{
        var valid = saveTaskValidator.isValid(saveTaskDto);
        if(!valid.isValid()) {
            throw new ValidationException(valid.getErrorMessages());
        }
        var mappedTask = mapper.map(saveTaskDto);
        return taskDao.create(mappedTask);
    }

    public static SaveTaskService getInstance(){
        return INSTANCE;
    }
}
