package com.miaskor.validator;

import com.miaskor.dto.SaveTaskDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.miaskor.util.ValidationVariable.MAX_LENGTH_TASK;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Deprecated(since = "since add javascript")
public class SaveTaskValidator implements Validator<List<SaveTaskDto>> {

    private static final SaveTaskValidator INSTANCE = new SaveTaskValidator();

    @Override
    public ValidationResult isValid(List<SaveTaskDto> saveTaskDtoList) {
        ValidationResult validationResult = new ValidationResult();
        List<String> listOfTasks = saveTaskDtoList.stream().map(SaveTaskDto::getTask)
                .collect(Collectors.toList());
        for(int index = 0;index<listOfTasks.size();index++){
            if(listOfTasks.get(index).length()>MAX_LENGTH_TASK){
                validationResult.add(new ErrorMessage("task","task "+(index+1)+" must be less"));
                break;
            }
        }
        return validationResult;
    }

    public static SaveTaskValidator getInstance(){
        return INSTANCE;
    }
}
