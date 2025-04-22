package com.taska.pm.service.impl;

import com.taska.pm.dto.task.TaskSaveDto;
import com.taska.pm.dto.task.TaskViewDto;
import com.taska.pm.dto.mapper.TaskMapper;
import com.taska.pm.entity.Project;
import com.taska.pm.entity.Task;
import com.taska.pm.entity.User;
import com.taska.pm.exception.ProjectNotFoundException;
import com.taska.pm.exception.TaskNotFoundException;
import com.taska.pm.exception.UserNotFoundException;
import com.taska.pm.exception.message.ExceptionMessages;
import com.taska.pm.repository.ProjectRepository;
import com.taska.pm.repository.TaskRepository;
import com.taska.pm.repository.UserRepository;
import com.taska.pm.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
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
    public TaskViewDto create(Long projectId, Long creatorId, TaskSaveDto taskSaveDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format(ExceptionMessages.PROJECT_NOT_FOUND, projectId)
                ));
        Task task = taskMapper.toEntity(taskSaveDto);
        Long userId = task.getResponsibleUser().getId();
        User responsibleUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format(ExceptionMessages.USER_NOT_FOUND, userId)
                ));
        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format(ExceptionMessages.USER_NOT_FOUND, creatorId)
                ));
        task.setProject(project);
        task.setResponsibleUser(responsibleUser);
        task.setCreator(creator);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public Optional<TaskViewDto> update(Long taskId, Long editorId, TaskSaveDto taskSaveDto) {
        return taskRepository.findById(taskId).map(existingTask -> {
            existingTask.setTitle(taskSaveDto.getTitle());
            existingTask.setDescription(taskSaveDto.getDescription());
            Long userId = taskSaveDto.getResponsibleUser();
            User responsibleUser = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(
                            String.format(ExceptionMessages.USER_NOT_FOUND, userId)
                    ));
            existingTask.setResponsibleUser(responsibleUser);
            User editor = userRepository.findById(editorId)
                    .orElseThrow(() -> new UserNotFoundException(
                            String.format(ExceptionMessages.USER_NOT_FOUND, editorId)
                    ));
            existingTask.setEditor(editor);
            existingTask.setIsEdited(true);
            Task updatedTask = taskRepository.save(existingTask);
            return taskMapper.toDto(updatedTask);
        });
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
