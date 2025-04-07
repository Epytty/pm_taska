package com.taska.pm.controller;

import com.taska.pm.dto.ProjectDto;
import com.taska.pm.entity.Project;
import com.taska.pm.exception.ProjectNotFoundException;
import com.taska.pm.service.ProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private static final Logger log = LogManager.getLogger(ProjectController.class);
    @Autowired
    public ProjectService projectService;

    @GetMapping
    public String projectsList(Model model) {
        List<ProjectDto> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/list";
    }

    @GetMapping("/new")
    public String newProjectPage(Model model) {
        return "project/new";
    }

    @PostMapping("/new")
    public String createNewProject(@ModelAttribute ProjectDto projectDto) {
        projectService.create(projectDto);
        return "redirect:/projects";
    }

    @GetMapping("/{id}")
    public String projectPage(@PathVariable(value = "id") Long id, Model model) {
        ProjectDto project = projectService.findById(id);
        model.addAttribute("project", project);
        return "project/project";
    }

    @GetMapping("/{id}/edit")
    public String editProjectPage(@PathVariable(value = "id") Long id,
                                  Model model) {
        ProjectDto project = projectService.findById(id);
        model.addAttribute("project", project);
        return "project/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProject(@PathVariable(value = "id") Long id, @ModelAttribute ProjectDto projectDto) {
        projectService.update(id, projectDto);
        return "redirect:/projects/{id}";
    }

    @GetMapping("/{id}/delete")
    public String deleteProject(@PathVariable(value = "id") Long id) {
        projectService.delete(id);
        return "redirect:/projects";
    }
}
