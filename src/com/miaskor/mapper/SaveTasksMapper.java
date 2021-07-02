package com.miaskor.mapper;

import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTasksMapper implements Mapper<List<SaveTaskDto>, List<Task>> {
    private static final SaveTasksMapper INSTANCE = new SaveTasksMapper();

    @Override
    public List<Task> map(List<SaveTaskDto> saveTaskDtoList) {
        List<Task> listOfTasks = new ArrayList<>();
        for(SaveTaskDto std : saveTaskDtoList){
            listOfTasks.add(Task.builder()
                    .taskName(std.getTask())
                    .clientId(std.getClientId())
                    .done(std.getDoneTask() != null)
                    .date(std.getDate())
                    .build());
        }
        return listOfTasks;
    }

    public static SaveTasksMapper getInstance(){
        return INSTANCE;
    }
}
