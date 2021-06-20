package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import com.miaskor.dto.FetchTaskDto;
import com.miaskor.mapper.FetchTasksMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FetchTasksService {
    private static final FetchTasksService INSTANCE = new FetchTasksService();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance();
    private final FetchTasksMapper mapper = FetchTasksMapper.getInstance();

    public Map<String, List<FetchTaskDto>> getTasks(LocalDate from, LocalDate to,Integer clientIndex){
        var taskByPeriod = taskDao.readAllTaskByPeriod(from, to,clientIndex);
        return mapper.map(taskByPeriod);
    }

    public static FetchTasksService getInstance(){
        return INSTANCE;
    }
}
