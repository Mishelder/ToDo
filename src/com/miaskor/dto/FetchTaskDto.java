package com.miaskor.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchTaskDto {
    String taskName;
    String done;
}
