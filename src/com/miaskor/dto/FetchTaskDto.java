package com.miaskor.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchTaskDto {
    Integer id;
    String taskName;
    String done;
}
