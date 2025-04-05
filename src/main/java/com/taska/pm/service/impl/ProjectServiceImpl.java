package com.taska.pm.service.impl;

import com.taska.pm.entity.Project;
import com.taska.pm.repository.ProjectRepository;
import com.taska.pm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    public ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void update(Long id, Project project) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        projectOptional.ifPresent(foundProject -> {
            foundProject.setName(project.getName());
            foundProject.setDescription(project.getDescription());

            projectRepository.save(foundProject);
        });
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
