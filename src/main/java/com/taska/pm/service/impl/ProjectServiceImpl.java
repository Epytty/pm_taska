package com.taska.pm.service.impl;

import com.taska.pm.dto.project.ProjectSaveDto;
import com.taska.pm.dto.project.ProjectViewDto;
import com.taska.pm.dto.mapper.ProjectMapper;
import com.taska.pm.entity.Project;
import com.taska.pm.entity.ProjectUsers;
import com.taska.pm.entity.User;
import com.taska.pm.enums.ProjectRole;
import com.taska.pm.exception.ProjectNotFoundException;
import com.taska.pm.exception.UserNotFoundException;
import com.taska.pm.exception.message.ExceptionMessages;
import com.taska.pm.repository.ProjectRepository;
import com.taska.pm.repository.ProjectsUsersRepository;
import com.taska.pm.repository.UserRepository;
import com.taska.pm.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectsUsersRepository projectsUsersRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectViewDto findById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDto)
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format(ExceptionMessages.PROJECT_NOT_FOUND, id)
                ));
    }

    @Override
    public List<ProjectViewDto> findAll() {
        return projectMapper.toDtos(projectRepository.findAll());
    }

    @Override
    public ProjectViewDto create(ProjectSaveDto projectSaveDto, Long creatorId) {
        Project project = projectMapper.toEntity(projectSaveDto);
        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format(ExceptionMessages.USER_NOT_FOUND, creatorId)
                ));
        project.setCreator(creator);
        Project savedProject = projectRepository.save(project);

        ProjectUsers projectUsers = new ProjectUsers();
        projectUsers.setProject(savedProject);
        projectUsers.setUser(creator);
        projectUsers.setProjectRole(ProjectRole.CREATOR);
        projectsUsersRepository.save(projectUsers);
        return projectMapper.toDto(savedProject);
    }

    @Override
    public Optional<ProjectViewDto> update(Long id, ProjectSaveDto projectSaveDto) {
        return projectRepository.findById(id).map(existingProject -> {
            existingProject.setName(projectSaveDto.getName());
            existingProject.setDescription(projectSaveDto.getDescription());
            Project updatedProject = projectRepository.save(existingProject);
            return projectMapper.toDto(updatedProject);
        });
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format(ExceptionMessages.PROJECT_NOT_FOUND, projectId)
                ));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format(ExceptionMessages.USER_NOT_FOUND, userId)
                ));
        ProjectUsers projectUsers = projectsUsersRepository.findByProjectId(projectId);
        project.getParticipants().add(user);
        projectsUsersRepository.save(projectUsers);
    }

    @Override
    public void excludeUserFromProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format(ExceptionMessages.PROJECT_NOT_FOUND, projectId)
                ));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format(ExceptionMessages.USER_NOT_FOUND, userId)
                ));
        ProjectUsers projectUsers = projectsUsersRepository.findByProjectId(projectId);
        project.getParticipants().remove(user);
        projectsUsersRepository.save(projectUsers);
    }
}
