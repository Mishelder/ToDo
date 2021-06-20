package com.miaskor.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FetchTaskDto {
    Integer indexInForm;
    String taskName;
    String done;
}
