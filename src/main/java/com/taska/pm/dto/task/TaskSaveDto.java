package com.taska.pm.dto.task;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskSaveDto {

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long responsibleUser;
    private Long creator;
    private Long editor;
    private Boolean isEdited;
}
