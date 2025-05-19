package com.taska.pm.service.impl;

import com.taska.pm.dto.project.ProjectViewDto;
import com.taska.pm.entity.ProjectUsers;
import com.taska.pm.exception.ProjectNotFoundException;
import com.taska.pm.exception.message.ExceptionMessages;
import com.taska.pm.repository.ProjectsUsersRepository;
import com.taska.pm.service.ProjectUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectUsersServiceImpl implements ProjectUsersService {

    private final ProjectsUsersRepository projectsUsersRepository;

    @Override
    public List<ProjectUsers> findAll() {
        return projectsUsersRepository.findAll();
    }

    @Override
    public ProjectUsers findById(Long id) {
        return projectsUsersRepository.findById(id)
            .orElseThrow(() -> new ProjectNotFoundException(
                String.format(ExceptionMessages.PROJECT_NOT_FOUND, id)));
    }
}
