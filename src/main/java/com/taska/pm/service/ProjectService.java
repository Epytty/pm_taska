package com.taska.pm.service;

import com.taska.pm.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();
    Project findById(Long id);
    void create(Project project);
    void update(Long id, Project project);
    void delete(Long id);
}
