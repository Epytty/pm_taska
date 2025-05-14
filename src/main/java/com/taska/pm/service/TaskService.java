package com.taska.pm.service;


import com.taska.pm.dto.task.TaskSaveDto;
import com.taska.pm.dto.task.TaskViewDto;
import com.taska.pm.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskViewDto findById(Long id);
    List<TaskViewDto> findAll();
    List<TaskViewDto> findByProjectId(Long projectId);
    TaskViewDto create(Long id, Long creatorId, TaskSaveDto taskSaveDto);
    Optional<TaskViewDto> update(Long id, Long editorId, TaskSaveDto taskSaveDto);
    void delete(Long id);
    void sendMessageToResponsibleUser(Task task, Long responsibleUserId);
}
