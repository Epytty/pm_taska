package com.taska.pm.controller;

import com.taska.pm.dto.user.UserRegisterDto;
import com.taska.pm.dto.user.UserViewDto;
import com.taska.pm.entity.Role;
import com.taska.pm.service.GroupService;
import com.taska.pm.service.RoleService;
import com.taska.pm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final GroupService groupService;

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

    @GetMapping("/newUser")
    public String newUserPage(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "admin/newUser";
    }

    @PostMapping("/newUser")
    public String registerUser(@ModelAttribute UserRegisterDto userRegisterDto) throws RoleNotFoundException {
        userService.register(userRegisterDto);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.delete(userId);
        return "redirect:/admin/users";
    }
}
