package com.miaskor.mapper;

import com.miaskor.cache.TasksCacheLRU;
import com.miaskor.dto.FetchTaskDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FetchTasksMapper implements Mapper<Map<LocalDate, List<Task>>,Map<String, List<FetchTaskDto>>> {
    private static final FetchTasksMapper INSTANCE = new FetchTasksMapper();
    private final FetchTaskMapper fetchTaskMapper = FetchTaskMapper.getInstance();

    @Override
    public Map<String, List<FetchTaskDto>> map(Map<LocalDate, List<Task>> from) {
        TasksCacheLRU tasksCacheLRU = new TasksCacheLRU();
        for(Map.Entry<LocalDate,List<Task>> e :from.entrySet()){
            tasksCacheLRU.put(e.getKey().toString(),fetchTaskMapper.map(e.getValue()));
        }
        return tasksCacheLRU.getMap();
    }

    public static FetchTasksMapper getInstance(){
        return INSTANCE;
    }
}
