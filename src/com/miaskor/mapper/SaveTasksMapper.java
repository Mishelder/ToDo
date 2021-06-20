package com.miaskor.mapper;

import com.miaskor.dto.SaveTaskDto;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTasksMapper implements Mapper<List<SaveTaskDto>, List<Task>> {
    private static final SaveTasksMapper INSTANCE = new SaveTasksMapper();

    @Override
    public List<Task> map(List<SaveTaskDto> saveTaskDtoList) {
        var tasks = saveTaskDtoList.stream()
                .filter(saveDto -> !saveDto.getTask().isEmpty())
                .collect(Collectors.toList());
        List<Task> listOfTasks = new ArrayList<>(15);
        for(SaveTaskDto std : tasks){
            listOfTasks.add(Task.builder()
                    .taskName(std.getTask())
                    .clientId(std.getClientId())
                    .done(std.getDoneTask() != null)
                    .date(std.getDate().toLocalDate())
                    .indexInForm(Integer.parseInt(std.getIndexInForm()))
                    .build());
        }
        return listOfTasks;
    }

    public static SaveTasksMapper getInstance(){
        return INSTANCE;
    }
}
