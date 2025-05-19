package com.taska.pm.dto.mapper;

import com.taska.pm.dto.mapper.util.ProjectMapperUtil;
import com.taska.pm.dto.project.ProjectSaveDto;
import com.taska.pm.dto.project.ProjectViewDto;
import com.taska.pm.entity.Project;
import com.taska.pm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                ProjectMapperUtil.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProjectMapper {

    @Mapping(target = "creatorFullName", qualifiedByName = {"ProjectMapperUtil", "getFullNameByUserId"}, source = "creator")
    ProjectViewDto toDto(Project project);

    Project toEntity(ProjectSaveDto projectDto);

    List<ProjectViewDto> toDtos(List<Project> projects);

    default Long map(User user) {
        return user != null ? user.getId() : null;
    }

    default User map(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }
}
