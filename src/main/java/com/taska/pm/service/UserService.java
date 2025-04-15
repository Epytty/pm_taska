package com.taska.pm.service;


import com.taska.pm.dto.user.UserRegisterDto;
import com.taska.pm.dto.user.UserUpdateDto;
import com.taska.pm.dto.user.UserViewDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);
    UserViewDto findById(Long id);
    List<UserViewDto> findAll();
    UserViewDto findCurrentUser();
    UserViewDto register(UserRegisterDto userRegisterDto) throws RoleNotFoundException;
    Optional<UserViewDto> update(Long id, UserUpdateDto userUpdateDto);
    void delete(Long id);
}
