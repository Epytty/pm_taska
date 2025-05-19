package com.taska.pm.controller;

import com.taska.pm.dto.project.ProjectSaveDto;
import com.taska.pm.dto.project.ProjectViewDto;
import com.taska.pm.dto.task.TaskSaveDto;
import com.taska.pm.dto.user.UserViewDto;
import com.taska.pm.entity.ProjectUsers;
import com.taska.pm.entity.User;
import com.taska.pm.service.CustomUserDetailsService;
import com.taska.pm.service.ProjectService;
import com.taska.pm.service.ProjectUsersService;
import com.taska.pm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectUsersService projectUsersService;
    private final UserService userService;

    @GetMapping
    public String projectsList(@AuthenticationPrincipal CustomUserDetailsService userDetails,
                               Model model) {
        List<ProjectViewDto> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/list";
    }

    @GetMapping("/new")
    public String newProjectPage(Model model) {
        return "project/new";
    }

    @PostMapping("/new")
    public String createNewProject(@AuthenticationPrincipal CustomUserDetailsService userDetails,
                                   @ModelAttribute ProjectSaveDto projectSaveDto) {
        Long creatorId = userDetails.getUser().getId();
        projectService.create(projectSaveDto, creatorId);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/edit")
    public String editProjectPage(@PathVariable(value = "id") Long id,
                                  Model model) {
        ProjectViewDto project = projectService.findById(id);
        ProjectUsers projectUsers = projectUsersService.findById(id);
        model.addAttribute("project", project);
        model.addAttribute("projectUsers", projectUsers);
        return "project/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProject(@PathVariable(value = "id") Long id,
                                @ModelAttribute ProjectSaveDto projectSaveDto) {
        projectService.update(id, projectSaveDto);
        return "redirect:/projects/{id}";
    }

    @GetMapping("/{projectId}/addUser")
    public String addUserPage(@PathVariable(value = "projectId") Long projectId, Model model) {
        List<UserViewDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "project/addUser";
    }

    @PostMapping("/{projectId}/addUser/{userId}")
    public String addUserToProject(@PathVariable(value = "projectId") Long projectId,
                                   @PathVariable(value = "userId") Long userId) {
        projectUsersService.findById(projectId);
        UserViewDto addedUser = userService.findById(userId);
        projectService.addUserToProject(projectId, addedUser.getId());
        return "redirect:/projects/{projectId}";
    }

    @PostMapping("/{projectId}/excludeUser/{userId}")
    public String excludeUserFromProject(@PathVariable(value = "projectId") Long projectId,
                                         @PathVariable(value = "userId") Long userId) {
        projectUsersService.findById(projectId);
        UserViewDto excludedUser = userService.findById(userId);
        projectService.excludeUserFromProject(projectId, excludedUser.getId());
        return "redirect:/projects/{projectId}/edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteProject(@PathVariable(value = "id") Long id) {
        projectService.delete(id);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/participants")
    public String projectUsersList(@PathVariable(value = "id") Long id,
                                   Model model) {

        return "project/participants";
    }
}
