package com.taska.pm.dto;

import lombok.Data;

@Data
public class TaskViewDto {

    private Long id;
    private String label;
    private String description;
}
