package com.taska.pm.dto.task;

import lombok.Data;

@Data
public class TaskSaveDto {

    private String title;
    private String description;
    private Long responsibleUser;
    private Long creator;
    private Long editor;
    private Boolean isEdited;
}
