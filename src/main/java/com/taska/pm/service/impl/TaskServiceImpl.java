package com.taska.pm.service.impl;

import com.taska.pm.dto.task.TaskCreateDto;
import com.taska.pm.dto.task.TaskViewDto;
import com.taska.pm.dto.mapper.TaskMapper;
import com.taska.pm.entity.Project;
import com.taska.pm.entity.Task;
import com.taska.pm.exception.ProjectNotFoundException;
import com.taska.pm.exception.TaskNotFoundException;
import com.taska.pm.exception.message.ExceptionMessages;
import com.taska.pm.repository.ProjectRepository;
import com.taska.pm.repository.TaskRepository;
import com.taska.pm.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskViewDto findById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto)
                .orElseThrow(() -> new TaskNotFoundException(
                        String.format(ExceptionMessages.TASK_NOT_FOUND, id)
                ));
    }

    @Override
    public List<TaskViewDto> findAll() {
        return taskMapper.toDtos(taskRepository.findAll());
    }

    @Override
    public List<TaskViewDto> findByProjectId(Long projectId) {
        return taskMapper.toDtos(taskRepository.findByProjectId(projectId));
    }

    @Override
    public TaskViewDto create(Long projectId, TaskCreateDto taskCreateDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format(ExceptionMessages.PROJECT_NOT_FOUND, projectId)
                ));
        Task task = taskMapper.toEntity(taskCreateDto);
        task.setProject(project);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public Optional<TaskViewDto> update(Long id, TaskCreateDto taskCreateDto) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setTitle(taskCreateDto.getTitle());
            existingTask.setDescription(taskCreateDto.getDescription());
            Task updatedTask = taskRepository.save(existingTask);
            return taskMapper.toDto(updatedTask);
        });
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
