package com.taska.pm.service.impl;

import com.taska.pm.dto.ProjectCreateDto;
import com.taska.pm.dto.ProjectViewDto;
import com.taska.pm.dto.mapper.ProjectMapper;
import com.taska.pm.entity.Project;
import com.taska.pm.exception.ProjectNotFoundException;
import com.taska.pm.exception.message.ExceptionMessages;
import com.taska.pm.repository.ProjectRepository;
import com.taska.pm.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
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
    public ProjectViewDto create(ProjectCreateDto projectCreateDto) {
        Project project = projectMapper.toEntity(projectCreateDto);
        return projectMapper.toDto(projectRepository.save(project));
    }

    @Override
    public Optional<ProjectViewDto> update(Long id, ProjectCreateDto projectCreateDto) {
        return projectRepository.findById(id).map(existingProject -> {
            existingProject.setName(projectCreateDto.getName());
            existingProject.setDescription(projectCreateDto.getDescription());
            Project updatedProject = projectRepository.save(existingProject);
            return projectMapper.toDto(updatedProject);
        });
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
