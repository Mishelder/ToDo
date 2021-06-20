package com.miaskor.mapper;

import com.miaskor.dto.FetchTaskDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTaskMapper implements Mapper<List<Task>,List<FetchTaskDto>> {
    private static final GetTaskMapper INSTANCE = new GetTaskMapper();
    private final FetchTasksMapper fetchTasksMapper = FetchTasksMapper.getInstance();

    @Override
    public List<FetchTaskDto> map(List<Task> object) {
        return fetchTasksMapper.mapListTasks(object);
    }

    public static GetTaskMapper getInstance(){
        return INSTANCE;
    }
}
