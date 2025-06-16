package com.taska.pm.dto.mapper;

import com.taska.pm.dto.group.GroupSaveDto;
import com.taska.pm.dto.group.GroupViewDto;
import com.taska.pm.dto.mapper.util.GroupMapperUtil;
import com.taska.pm.entity.Group;
import com.taska.pm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
               GroupMapperUtil.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface GroupMapper {

    @Mapping(target = "leadFullName", qualifiedByName = {"GroupMapperUtil", "getFullNameByUserId"}, source = "lead")
    GroupViewDto toDto(Group group);

    Group toEntityFromViewDto(GroupViewDto groupViewDto);

    Group toEntity(GroupSaveDto groupSaveDto);

    List<GroupViewDto> toDtos(List<Group> groups);

    default Long map(User user) {
        return user != null ? user.getId() : null;
    }

    default User map(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }
}
