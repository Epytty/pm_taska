package com.taska.pm.dto.group;


import com.taska.pm.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class GroupViewDto {

    private Long id;
    private String name;
    private String leadFullName;
    private List<User> members;
}
