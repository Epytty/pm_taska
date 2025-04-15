package com.taska.pm.dto.mapper;

import com.taska.pm.dto.user.UserRegisterDto;
import com.taska.pm.dto.user.UserViewDto;
import com.taska.pm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserViewDto toDto(User user);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", ignore = true)
    User toEntity(UserRegisterDto userRegisterDto);

    List<UserViewDto> toDtos(List<User> users);
}
