package com.taska.pm.service.impl;

import com.taska.pm.dto.ProjectDto;
import com.taska.pm.dto.mapper.ProjectMapper;
import com.taska.pm.entity.Project;
import com.taska.pm.exception.ProjectNotFoundException;
import com.taska.pm.exception.message.ExceptionMessages;
import com.taska.pm.repository.ProjectRepository;
import com.taska.pm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public ProjectMapper projectMapper;

    @Override
    public ProjectDto findById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDto)
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format(ExceptionMessages.PROJECT_NOT_FOUND, id))
                );
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectMapper.toDtos(projectRepository.findAll());
    }

    @Override
    public ProjectDto create(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        return projectMapper.toDto(projectRepository.save(project));
    }

    @Override
    public Optional<ProjectDto> update(Long id, ProjectDto projectDto) {
        return projectRepository.findById(id).map(existingProject -> {
            existingProject.setName(projectDto.getName());
            existingProject.setDescription(projectDto.getDescription());
            Project updatedProject = projectRepository.save(existingProject);
            return projectMapper.toDto(updatedProject);
        });
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
