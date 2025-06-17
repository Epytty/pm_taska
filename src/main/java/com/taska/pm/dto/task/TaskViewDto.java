package com.taska.pm.dto.task;

import com.taska.pm.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskViewDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskStatus status;
    private String responsibleUserFullName;
    private String creatorFullName;
    private String editorFullName;
    private Boolean isEdited;
}
