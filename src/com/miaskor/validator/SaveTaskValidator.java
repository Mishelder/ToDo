package com.miaskor.validator;

import com.miaskor.dto.SaveTaskDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.miaskor.util.ValidationVariable.MAX_LENGTH_TASK;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveTaskValidator implements Validator<SaveTaskDto> {

    private static final SaveTaskValidator INSTANCE = new SaveTaskValidator();

    @Override
    public ValidationResult isValid(SaveTaskDto saveTaskDtoList) {
        ValidationResult validationResult = new ValidationResult();
        if(saveTaskDtoList.getTask().length()>MAX_LENGTH_TASK){
            validationResult.add(new ErrorMessage("task","task must be less"));
        }
        return validationResult;
    }

    public static SaveTaskValidator getInstance(){
        return INSTANCE;
    }
}
