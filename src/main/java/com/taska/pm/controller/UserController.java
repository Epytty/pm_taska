package com.taska.pm.controller;

import com.taska.pm.dto.user.UserUpdateDto;
import com.taska.pm.dto.user.UserViewDto;
import com.taska.pm.entity.Group;
import com.taska.pm.service.CustomUserDetailsService;
import com.taska.pm.service.GroupService;
import com.taska.pm.service.RoleService;
import com.taska.pm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final GroupService groupService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "user/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        UserViewDto user = userService.findCurrentUser();
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/profile/edit")
    public String editProfilePage(Model model) {
        UserViewDto userViewDto = userService.findCurrentUser();
        model.addAttribute("user", userViewDto);
        return "user/edit";
    }

    @PostMapping("/profile/edit")
    public String updateUser(@AuthenticationPrincipal CustomUserDetailsService userDetails,
                             @ModelAttribute UserUpdateDto userUpdateDto) {
        userService.update(userDetails.getUser().getId(), userUpdateDto);
        return "redirect:/profile";
    }
}
