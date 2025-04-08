package com.taska.pm.controller;

import com.taska.pm.dto.ProjectCreateDto;
import com.taska.pm.dto.ProjectViewDto;
import com.taska.pm.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public String projectsList(Model model) {
        List<ProjectViewDto> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/list";
    }

    @GetMapping("/new")
    public String newProjectPage(Model model) {
        return "project/new";
    }

    @PostMapping("/new")
    public String createNewProject(@ModelAttribute ProjectCreateDto projectCreateDto) {
        projectService.create(projectCreateDto);
        return "redirect:/projects";
    }

//    @GetMapping("/{id}")
//    public String projectPage(@PathVariable(value = "id") Long id, Model model) {
//        ProjectViewDto project = projectService.findById(id);
//        model.addAttribute("project", project);
//        return "project/view";
//    }

    @GetMapping("/{id}/edit")
    public String editProjectPage(@PathVariable(value = "id") Long id,
                                  Model model) {
        ProjectViewDto project = projectService.findById(id);
        model.addAttribute("project", project);
        return "project/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProject(@PathVariable(value = "id") Long id,
                                @ModelAttribute ProjectCreateDto projectCreateDto) {
        projectService.update(id, projectCreateDto);
        return "redirect:/projects/{id}";
    }

    @GetMapping("/{id}/delete")
    public String deleteProject(@PathVariable(value = "id") Long id) {
        projectService.delete(id);
        return "redirect:/projects";
    }
}
