package com.miaskor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveTaskDto {
    String task;
    String doneTask;
    String date;
    @JsonIgnore
    Integer clientId;
}
