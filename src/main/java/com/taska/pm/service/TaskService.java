package com.taska.pm.service;


import com.taska.pm.dto.TaskCreateDto;
import com.taska.pm.dto.TaskViewDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskViewDto findById(Long id);
    List<TaskViewDto> findAll();
    List<TaskViewDto> findByProjectId(Long projectId);
    TaskViewDto create(Long id, TaskCreateDto taskCreateDto);
    Optional<TaskViewDto> update(Long id, TaskCreateDto taskCreateDto);
    void delete(Long id);
}
