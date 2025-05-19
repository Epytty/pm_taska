package com.taska.pm.dto.projectUsers;

import com.taska.pm.dto.user.UserViewDto;
import com.taska.pm.enums.ProjectRole;
import lombok.Data;

@Data
public class ProjectUsersDto {

    private UserViewDto user;
    private ProjectRole projectRole;
}
