package com.taska.pm.dto.mapper;

import com.taska.pm.dto.TaskCreateDto;
import com.taska.pm.dto.TaskViewDto;
import com.taska.pm.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskViewDto toDto(Task task);

    Task toEntity(TaskCreateDto taskCreateDto);

    List<TaskViewDto> toDtos(List<Task> tasks);
}
