package com.taska.pm.service;

import com.taska.pm.dto.project.ProjectCreateDto;
import com.taska.pm.dto.project.ProjectViewDto;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<ProjectViewDto> findAll();
    ProjectViewDto findById(Long id);
    ProjectViewDto create(ProjectCreateDto projectCreateDto);
    Optional<ProjectViewDto> update(Long id, ProjectCreateDto projectCreateDto);
    void delete(Long id);
}
