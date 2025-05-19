package com.taska.pm.dto.project;

import com.taska.pm.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ProjectSaveDto {

    private String name;
    private String description;
    private Long creator;
}
