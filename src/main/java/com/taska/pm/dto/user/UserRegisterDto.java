package com.taska.pm.dto.user;

import lombok.Data;

@Data
public class UserRegisterDto {

    private String username;
    private String password;
    private String role;
}
