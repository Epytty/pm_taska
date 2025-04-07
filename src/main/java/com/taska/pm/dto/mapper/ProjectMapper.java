package com.taska.pm.dto.mapper;

import com.taska.pm.dto.ProjectDto;
import com.taska.pm.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDto toDto(Project project);

    Project toEntity(ProjectDto projectDto);

    List<ProjectDto> toDtos(List<Project> projects);
}
