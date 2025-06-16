package com.taska.pm.dto.group;

import com.taska.pm.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class GroupSaveDto {

    private Long id;
    private String name;
    private User lead;
}
