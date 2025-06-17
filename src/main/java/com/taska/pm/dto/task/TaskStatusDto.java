package com.taska.pm.dto.task;

import com.taska.pm.enums.TaskStatus;
import lombok.Data;

@Data
public class TaskStatusDto {

    private TaskStatus status;
}
