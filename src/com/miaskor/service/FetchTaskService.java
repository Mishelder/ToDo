package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.FetchTaskDto;
import com.miaskor.mapper.FetchTaskMapper;
import com.miaskor.mapper.FetchTasksMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FetchTaskService {
    private static final FetchTaskService INSTANCE = new FetchTaskService();
    private final FetchTaskMapper fetchTaskMapper = FetchTaskMapper.getInstance();
    private final FetchTasksMapper fetchTasksMapper = FetchTasksMapper.getInstance();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance(false);

    public List<FetchTaskDto> getTask(LocalDate day,Integer clientIndex){
        var tasks = taskDao.readByDate(day, clientIndex);
        return fetchTaskMapper.map(tasks);
    }

    public Map<String, List<FetchTaskDto>> getTasks(LocalDate from, LocalDate to, Integer clientIndex){
        var taskByPeriod = taskDao.readAllTaskByPeriod(from, to,clientIndex);
        return fetchTasksMapper.map(taskByPeriod);
    }

    public static FetchTaskService getInstance(){
        return INSTANCE;
    }
}
