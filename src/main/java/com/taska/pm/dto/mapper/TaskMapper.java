package com.taska.pm.dto.mapper;

import com.taska.pm.dto.mapper.util.TaskMapperUtil;
import com.taska.pm.dto.task.TaskSaveDto;
import com.taska.pm.dto.task.TaskStatusDto;
import com.taska.pm.dto.task.TaskViewDto;
import com.taska.pm.entity.Task;
import com.taska.pm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                TaskMapperUtil.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TaskMapper {

    @Mapping(target = "responsibleUserFullName", qualifiedByName = {"TaskMapperUtil", "getFullNameByUserId"}, source = "responsibleUser")
    @Mapping(target = "creatorFullName", qualifiedByName = {"TaskMapperUtil", "getFullNameByUserId"}, source = "creator")
    @Mapping(target = "editorFullName", qualifiedByName = {"TaskMapperUtil", "getFullNameByUserId"}, source = "editor")
    TaskViewDto toDto(Task task);

    Task toEntityFromStatusDto(TaskStatusDto taskStatusDto);
    Task toEntityFromViewDto(TaskViewDto taskViewDto);

    Task toEntity(TaskSaveDto taskSaveDto);

    List<TaskViewDto> toDtos(List<Task> tasks);

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
