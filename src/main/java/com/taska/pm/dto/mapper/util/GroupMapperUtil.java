package com.taska.pm.dto.mapper.util;

import com.taska.pm.dto.user.UserViewDto;
import com.taska.pm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("GroupMapperUtil")
@Component
@RequiredArgsConstructor
public class GroupMapperUtil {

    private final UserService userService;

    @Named("getFullNameByUserId")
    public String getFullNameByUserId(Long id) {
        if (id == null) {
            return null;
        }
        UserViewDto user = userService.findById(id);
        return String.format("%s %s %s", user.getLastName(), user.getFirstName(), user.getMiddleName()).trim();
    }
}
