package com.taska.pm.dto.project;

import com.taska.pm.dto.projectUsers.ProjectUsersDto;
import lombok.Data;

import java.util.List;

@Data
public class ProjectViewDto {

    private Long id;
    private String name;
    private String description;
    private String creatorFullName;
    private List<ProjectUsersDto> participants;
}
