package com.taska.pm.service;

import com.taska.pm.dto.project.ProjectSaveDto;
import com.taska.pm.dto.project.ProjectViewDto;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<ProjectViewDto> findAll();
    ProjectViewDto findById(Long id);
    ProjectViewDto create(ProjectSaveDto projectSaveDto, Long creatorId);
    Optional<ProjectViewDto> update(Long id, ProjectSaveDto projectSaveDto);
    void delete(Long id);
    void addUserToProject(Long projectId, Long userId);
    void excludeUserFromProject(Long projectId, Long userId);
}
