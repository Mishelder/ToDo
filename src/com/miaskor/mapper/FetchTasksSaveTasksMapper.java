package com.miaskor.mapper;

import com.miaskor.dto.FetchTaskDto;
import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FetchTasksSaveTasksMapper implements Mapper<List<SaveTaskDto>,List<FetchTaskDto>> {
    private static final FetchTasksSaveTasksMapper INSTANCE = new FetchTasksSaveTasksMapper();

    @Override
    public List<FetchTaskDto> map(List<SaveTaskDto> object) {
        var tasks = object.stream()
                .filter(saveDto -> !saveDto.getTask().isEmpty())
                .collect(Collectors.toList());
        List<FetchTaskDto> listOfTasks = new ArrayList<>();
        for(SaveTaskDto std : tasks)
            listOfTasks.add(FetchTaskDto.builder()
                    .done(std.getDoneTask()!=null?"checked":"")
                    .taskName(std.getTask())
                    .indexInForm(Integer.parseInt(std.getIndexInForm()))
                    .build());
        return listOfTasks;
    }

    public static FetchTasksSaveTasksMapper getInstance(){
        return INSTANCE;
    }
}
