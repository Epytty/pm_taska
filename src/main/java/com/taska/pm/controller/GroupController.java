package com.taska.pm.controller;

import com.taska.pm.dto.group.GroupSaveDto;
import com.taska.pm.dto.group.GroupViewDto;
import com.taska.pm.dto.user.UserViewDto;
import com.taska.pm.entity.User;
import com.taska.pm.service.GroupService;
import com.taska.pm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final UserService userService;
    private final GroupService groupService;


    @GetMapping("/groups")
    public String groupsPage(Model model) {
        List<GroupViewDto> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "group/list";
    }


    @GetMapping("/newGroup")
    public String newGroupPage(Model model) {
        List<UserViewDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "group/new";
    }

    @PostMapping("/newGroup")
    public String createGroup(@ModelAttribute GroupSaveDto groupDto) {
        groupService.create(groupDto);
        return "redirect:/groups/list";
    }

    @GetMapping("/groups/{groupId}")
    public String groupPage(@PathVariable(value = "groupId") Long groupId,
                            Model model) {
        GroupViewDto group = groupService.findById(groupId);
        model.addAttribute("group", group);
        List<User> members = group.getMembers();
        model.addAttribute("members", members);
        return "group/show";
    }

    @GetMapping("/groups/{groupId}/update")
    public String editGroupPage(@PathVariable(value = "groupId") Long groupId,
                                Model model) {
        GroupViewDto group = groupService.findById(groupId);
        model.addAttribute("group", group);
        return "group/edit";
    }

    @PostMapping("/groups/{groupId}/update")
    public String updateGroup(@ModelAttribute GroupSaveDto groupDto) {
        Long groupId = groupDto.getId();
        groupService.update(groupId, groupDto);
        return "redirect:/groups/list";
    }

    @GetMapping("/groups/{groupId}/delete")
    public String deleteGroup(@PathVariable(value = "groupId") Long groupId) {
        groupService.delete(groupId);
        return "redirect:/groups/list";
    }

    @GetMapping("/groups/{groupId}/addUser")
    public String addUserPage(@PathVariable(value = "groupId") Long groupId,
                              Model model) {
        GroupViewDto group = groupService.findById(groupId);
        model.addAttribute("group", group);
        List<UserViewDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "group/addUser";
    }

    @PostMapping("/groups/{groupId}/addUser")
    public String addUserToGroup(@PathVariable(value = "groupId") Long groupId,
                                 @RequestParam(value = "userId") Long userId) {
        GroupViewDto group = groupService.findById(groupId);
        groupService.addUserToGroup(group, userId);
        return "redirect:/groups/{groupId}";
    }

    @GetMapping("/groups/{groupId}/excludeUser")
    public String excludeUserFromPGroup(@PathVariable(value = "groupId") Long groupId,
                                        @RequestParam(value = "userId") Long userId) {
        GroupViewDto group = groupService.findById(groupId);
        groupService.excludeUserFromGroup(group, userId);
        return "redirect:/groups/{groupId}";
    }
}
