package com.miaskor.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Task {
    private Integer id;
    private Integer clientId;
    private String taskName;
    private Boolean done;
    private LocalDate date;
    private Integer indexInForm;
}
