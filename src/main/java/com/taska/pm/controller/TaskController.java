package com.taska.pm.controller;

import com.taska.pm.dto.project.ProjectViewDto;
import com.taska.pm.dto.task.TaskCreateDto;
import com.taska.pm.dto.task.TaskViewDto;
import com.taska.pm.service.ProjectService;
import com.taska.pm.service.TaskService;
import lombok.RequiredArgsConstructor;
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
        model.addAttribute("projectId", projectId);
        return "task/new";
    }

    @PostMapping("/new")
    public String createNewTask(@PathVariable(name = "projectId") Long projectId,
                                @ModelAttribute TaskCreateDto taskCreateDto,
                                Model model) {
        taskService.create(projectId, taskCreateDto);
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
        TaskViewDto task = taskService.findById(taskId);
        model.addAttribute("task", task);
        return "task/edit";
    }

    @PostMapping("/{taskId}/edit")
    public String updateTask(@PathVariable(name = "projectId") Long projectId,
                             @PathVariable(name = "taskId") Long taskId,
                             @ModelAttribute TaskCreateDto taskCreateDto) {
        taskService.update(taskId, taskCreateDto);
        return "redirect:/projects/{projectId}/{taskId}";
    }

    @GetMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable(name = "projectId") Long projectId,
                             @PathVariable(name = "taskId") Long taskId) {
        taskService.delete(taskId);
        return "redirect:/projects/{projectId}";
    }
}
