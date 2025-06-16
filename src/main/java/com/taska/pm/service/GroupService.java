package com.taska.pm.service;


import com.taska.pm.dto.group.GroupSaveDto;
import com.taska.pm.dto.group.GroupViewDto;
import com.taska.pm.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    GroupViewDto findById(Long groupId);
    List<GroupViewDto> findAll();
    GroupViewDto create(GroupSaveDto groupDto);
    Optional<GroupViewDto> update(Long groupId, GroupSaveDto groupDto);
    void delete(Long groupId);
    void addUserToGroup(GroupViewDto groupDto, Long userId);
    void excludeUserFromGroup(GroupViewDto groupDto, Long userId);
}
