package com.miaskor.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class SaveTaskDto {
    String task;
    String doneTask;
    LocalDate date;
    Integer clientId;
}
