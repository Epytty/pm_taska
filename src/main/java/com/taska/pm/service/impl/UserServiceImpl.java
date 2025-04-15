package com.taska.pm.service.impl;

import com.taska.pm.dto.mapper.UserMapper;
import com.taska.pm.dto.user.UserRegisterDto;
import com.taska.pm.dto.user.UserUpdateDto;
import com.taska.pm.dto.user.UserViewDto;
import com.taska.pm.entity.Role;
import com.taska.pm.entity.User;
import com.taska.pm.exception.UserNotFoundException;
import com.taska.pm.exception.message.ExceptionMessages;
import com.taska.pm.repository.RoleRepository;
import com.taska.pm.repository.UserRepository;
import com.taska.pm.service.CustomUserDetailsService;
import com.taska.pm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(CustomUserDetailsService::new)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(ExceptionMessages.USERNAME_NOT_FOUND, username)
                ));
    }

    @Override
    public List<UserViewDto> findAll() {
        return userMapper.toDtos(userRepository.findAll());
    }

    @Override
    public UserViewDto findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format(ExceptionMessages.USER_NOT_FOUND, id)
            ));
    }

    @Override
    public UserViewDto findCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User  not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserViewDto register(UserRegisterDto userRegisterDto) throws RoleNotFoundException {
        User user = userMapper.toEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Role role = roleRepository.findByName(userRegisterDto.getRole())
                .orElseThrow(() -> new RoleNotFoundException(
                        String.format(ExceptionMessages.ROLE_NOT_FOUND, userRegisterDto.getRole())
                ));
        user.setRole(role);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public Optional<UserViewDto> update(Long id, UserUpdateDto userUpdateDto) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setFirstName(userUpdateDto.getFirstName());
            existingUser.setMiddleName(userUpdateDto.getMiddleName());
            existingUser.setLastName(userUpdateDto.getLastName());
            User updatedUser = userRepository.save(existingUser);
            return userMapper.toDto(updatedUser);
        });
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
