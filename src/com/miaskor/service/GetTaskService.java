package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.FetchTaskDto;
import com.miaskor.mapper.GetTaskMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTaskService {
    private static final GetTaskService INSTANCE = new GetTaskService();
    private final GetTaskMapper mapper = GetTaskMapper.getInstance();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance();

    public List<FetchTaskDto> getTask(LocalDate day,Integer clientIndex){
        var tasks = taskDao.readByDate(day, clientIndex);
        return mapper.map(tasks);
    }

    public static GetTaskService getInstance(){
        return INSTANCE;
    }
}
