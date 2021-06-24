package com.miaskor.mapper;

import com.miaskor.dto.FetchTaskDto;
import com.miaskor.dto.SaveTaskDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTaskFetchTaskMapper implements Mapper<List<SaveTaskDto>,List<FetchTaskDto>> {
    private static final SaveTaskFetchTaskMapper INSTANCE = new SaveTaskFetchTaskMapper();

    @Override
    public List<FetchTaskDto> map(List<SaveTaskDto> saveTaskDtos) {
        List<FetchTaskDto> listOfTasks = new ArrayList<>();
        for(SaveTaskDto std : saveTaskDtos)
            listOfTasks.add(FetchTaskDto.builder()
                    .done(std.getDoneTask()!=null?"checked":"")
                    .taskName(std.getTask())
                    .indexInForm(Integer.parseInt(std.getIndexInForm()))
                    .build());
        return listOfTasks;
    }

    public static SaveTaskFetchTaskMapper getInstance(){
        return INSTANCE;
    }
}
