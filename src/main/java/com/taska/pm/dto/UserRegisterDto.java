package com.taska.pm.dto;

import com.taska.pm.entity.Role;
import lombok.Data;

@Data
public class UserRegisterDto {

    private String username;
    private String password;
    private String role;
}
