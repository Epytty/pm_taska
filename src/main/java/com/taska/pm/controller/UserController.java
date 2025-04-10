package com.taska.pm.controller;

import com.taska.pm.dto.UserRegisterDto;
import com.taska.pm.entity.Role;
import com.taska.pm.service.RoleService;
import com.taska.pm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "user/login";
    }

    @GetMapping("/register")
    public String newUserPage(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegisterDto userRegisterDto) throws RoleNotFoundException {
        userService.register(userRegisterDto);
        return "redirect:/login";
    }
}
