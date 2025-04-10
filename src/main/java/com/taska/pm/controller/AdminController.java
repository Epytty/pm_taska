package com.taska.pm.controller;

import com.taska.pm.dto.UserViewDto;
import com.taska.pm.service.RoleService;
import com.taska.pm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String adminPanelPage(Model model) {
        return "admin/panel";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        List<UserViewDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }

}
