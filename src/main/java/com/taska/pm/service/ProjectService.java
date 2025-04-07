package com.taska.pm.service;

import com.taska.pm.dto.ProjectDto;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<ProjectDto> findAll();
    ProjectDto findById(Long id);
    ProjectDto create(ProjectDto projectDto);
    Optional<ProjectDto> update(Long id, ProjectDto projectDto);
    void delete(Long id);
}
