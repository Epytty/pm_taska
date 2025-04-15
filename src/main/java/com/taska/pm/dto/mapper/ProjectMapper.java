package com.taska.pm.dto.mapper;

import com.taska.pm.dto.project.ProjectCreateDto;
import com.taska.pm.dto.project.ProjectViewDto;
import com.taska.pm.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProjectMapper {

    ProjectViewDto toDto(Project project);

    Project toEntity(ProjectCreateDto projectDto);

    List<ProjectViewDto> toDtos(List<Project> projects);
}
