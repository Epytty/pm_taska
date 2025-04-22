package com.taska.pm.dto.task;

import lombok.Data;

@Data
public class TaskViewDto {

    private Long id;
    private String title;
    private String description;
    private String responsibleUserFullName;
    private String creatorFullName;
    private String editorFullName;
    private Boolean isEdited;
}
