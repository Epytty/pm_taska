package com.taska.pm.service;


import com.taska.pm.entity.ProjectUsers;

import java.util.List;

public interface ProjectUsersService {

    List<ProjectUsers> findAll();
    ProjectUsers findById(Long id);
}
