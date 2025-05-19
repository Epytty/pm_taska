package com.taska.pm.dto.mapper;

import com.taska.pm.dto.mapper.util.ProjectUsersMapperUtil;
import com.taska.pm.dto.projectUsers.ProjectUsersDto;
import com.taska.pm.entity.ProjectUsers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                ProjectUsersMapperUtil.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProjectUsersMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "projectRole", source = "projectRole")
    ProjectUsersDto toDto(ProjectUsers projectUsers);

    List<ProjectUsersDto> toDtos(List<ProjectUsers> projectUsers);
}
