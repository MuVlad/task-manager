package com.muslimov.vlad.taskmanager.mapper;

import com.muslimov.vlad.taskmanager.dto.TaskCreateDto;
import com.muslimov.vlad.taskmanager.dto.TaskDto;
import com.muslimov.vlad.taskmanager.dto.TaskUpdateDto;
import com.muslimov.vlad.taskmanager.model.Task;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TaskMapper {

    TaskDto toDto(Task task);

    Task toEntity(TaskCreateDto taskCreateDto);

    void updateTask(@MappingTarget Task task, TaskUpdateDto taskUpdateDto);
}
