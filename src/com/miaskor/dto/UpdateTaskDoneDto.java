package com.miaskor.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDoneDto {
    Integer id;
    Boolean isDone;
}
