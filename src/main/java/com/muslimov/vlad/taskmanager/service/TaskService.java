package com.muslimov.vlad.taskmanager.service;

import com.muslimov.vlad.taskmanager.dto.TaskCreateDto;
import com.muslimov.vlad.taskmanager.dto.TaskDto;
import com.muslimov.vlad.taskmanager.dto.TaskUpdateDto;
import com.muslimov.vlad.taskmanager.mapper.TaskMapper;
import com.muslimov.vlad.taskmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskDto> getAll() {

        return taskRepository
            .findAll()
            .stream()
            .map(taskMapper::toDto)
            .toList();
    }

    public TaskDto getById(Long id) {

        final var task = taskRepository.findByIdOrThrow(id);
        return taskMapper.toDto(task);
    }

    @Transactional
    public TaskDto createTask(TaskCreateDto taskCreateDto) {

        final var newTask = taskMapper.toEntity(taskCreateDto);
        return taskMapper.toDto(taskRepository.save(newTask));
    }

    @Transactional
    public TaskDto updateTask(TaskUpdateDto taskUpdateDto) {

        final var task = taskRepository.findByIdOrThrow(taskUpdateDto.getId());
        taskMapper.updateTask(task, taskUpdateDto);
        return taskMapper.toDto(task);

    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}