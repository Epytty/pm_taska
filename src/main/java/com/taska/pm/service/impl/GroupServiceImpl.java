package com.taska.pm.service.impl;

import com.taska.pm.dto.group.GroupSaveDto;
import com.taska.pm.dto.group.GroupViewDto;
import com.taska.pm.dto.mapper.GroupMapper;
import com.taska.pm.dto.mapper.UserMapper;
import com.taska.pm.entity.Group;
import com.taska.pm.entity.User;
import com.taska.pm.exception.GroupNotFoundException;
import com.taska.pm.exception.message.ExceptionMessages;
import com.taska.pm.repository.GroupRepository;
import com.taska.pm.repository.UserRepository;
import com.taska.pm.service.GroupService;
import com.taska.pm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;

    @Override
    public GroupViewDto findById(Long groupId) {
        return groupRepository.findById(groupId)
                .map(groupMapper::toDto)
                .orElseThrow(() -> new GroupNotFoundException(
                        String.format(ExceptionMessages.GROUP_NOT_FOUND, groupId)));
    }

    @Override
    public List<GroupViewDto> findAll() {
        return groupMapper.toDtos(groupRepository.findAll());
    }

    @Override
    public GroupViewDto create(GroupSaveDto groupSaveDto) {
        User lead = userMapper.toEntityFromViewDto(
                userService.findById(groupSaveDto.getLead().getId()));
        Group group = groupMapper.toEntity(groupSaveDto);
        group.setName(group.getName());
        group.setLead(lead);
        lead.setGroup(group);
        Group savedGroup = groupRepository.save(group);
        return groupMapper.toDto(savedGroup);
    }

    @Override
    public Optional<GroupViewDto> update(Long groupId, GroupSaveDto groupDto) {
        return groupRepository.findById(groupId).map(existingGroup -> {
            User lead = userMapper.toEntityFromViewDto(
                    userService.findById(groupDto.getLead().getId()));
            existingGroup.setName(groupDto.getName());
            existingGroup.setLead(lead);
            Group updatedGroup = groupRepository.save(existingGroup);
            return groupMapper.toDto(updatedGroup);
        });
    }

    @Override
    public void delete(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public void addUserToGroup(GroupViewDto groupViewDto, Long userId) {
        Group group = groupMapper.toEntityFromViewDto(groupViewDto);
        User user = userMapper.toEntityFromViewDto(
                userService.findById(userId));
        user.setGroup(group);
        userRepository.save(user);
    }

    @Override
    public void excludeUserFromGroup(GroupViewDto groupViewDto, Long userId) {
        Group group = groupMapper.toEntityFromViewDto(groupViewDto);
        User user = userMapper.toEntityFromViewDto(
                userService.findById(userId));
        user.setGroup(null);
        userRepository.save(user);
    }
}
