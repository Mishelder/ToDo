package com.miaskor.dto;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder
public class SaveTaskDto {
    String task;
    String doneTask;
    ZonedDateTime date;
    Integer clientId;
    String indexInForm;
}
