package com.taska.pm.controller;

import com.taska.pm.dto.project.ProjectViewDto;
import com.taska.pm.dto.task.TaskSaveDto;
import com.taska.pm.dto.task.TaskStatusDto;
import com.taska.pm.dto.task.TaskViewDto;
import com.taska.pm.dto.user.UserViewDto;
import com.taska.pm.entity.ProjectUsers;
import com.taska.pm.repository.ProjectsUsersRepository;
import com.taska.pm.repository.UserRepository;
import com.taska.pm.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects/{projectId}")
@RequiredArgsConstructor
public class TaskController {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping
    public String projectPage(@PathVariable(name = "projectId") Long projectId,
                              Model model) {
        ProjectViewDto project = projectService.findById(projectId);
        List<TaskViewDto> tasks = taskService.findByProjectId(projectId);
        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);
        return "project/view";
    }

    @GetMapping("/new")
    public String newTaskPage(@PathVariable(name = "projectId") Long projectId,
                              Model model) {
        List<UserViewDto> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("projectId", projectId);
        return "task/new";
    }

    @PostMapping("/new")
    public String createNewTask(@PathVariable(name = "projectId") Long projectId,
                                @AuthenticationPrincipal CustomUserDetailsService userDetails,
                                @ModelAttribute TaskSaveDto taskSaveDto) {
        Long creatorId = userDetails.getUser().getId();
        taskService.create(projectId, creatorId, taskSaveDto);
        return "redirect:/projects/{projectId}";
    }

    @GetMapping("/{taskId}")
    public String taskPage(@PathVariable(name = "projectId") Long projectId,
                           @PathVariable(name = "taskId") Long taskId,
                           Model model) {
        TaskViewDto task = taskService.findById(taskId);
        model.addAttribute("task", task);
        return "task/view";
    }

    @GetMapping("/{taskId}/edit")
    public String editTaskPage(@PathVariable(name = "projectId") Long projectId,
                               @PathVariable(name = "taskId") Long taskId,
                               Model model) {
        List<UserViewDto> users = userService.findAll();
        model.addAttribute("users", users);
        TaskViewDto task = taskService.findById(taskId);
        model.addAttribute("task", task);
        return "task/edit";
    }

    @PostMapping("/{taskId}/edit")
    public String updateTask(@PathVariable(name = "projectId") Long projectId,
                             @PathVariable(name = "taskId") Long taskId,
                             @AuthenticationPrincipal CustomUserDetailsService userDetails,
                             @ModelAttribute TaskSaveDto taskSaveDto) {
        Long editorId = userDetails.getUser().getId();
        taskService.update(taskId, editorId, taskSaveDto);
        return "redirect:/projects/{projectId}/{taskId}";
    }

    @PostMapping("/{taskId}/changeStatus")
    public String changeTaskStatus(@PathVariable(name = "projectId") Long projectId,
                                   @PathVariable(name = "taskId") Long taskId,
                                   @ModelAttribute TaskStatusDto taskStatusDto) {
        taskService.changeTaskStatus(taskId, taskStatusDto);
        return "redirect:/projects/{projectId}/{taskId}";
    }

    @GetMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable(name = "projectId") Long projectId,
                             @PathVariable(name = "taskId") Long taskId) {
        taskService.delete(taskId);
        return "redirect:/projects/{projectId}";
    }
}
