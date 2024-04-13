package com.muslimov.vlad.taskmanager.service;

import com.muslimov.vlad.taskmanager.dto.TaskCreateDto;
import com.muslimov.vlad.taskmanager.dto.TaskDto;
import com.muslimov.vlad.taskmanager.dto.TaskUpdateDto;
import com.muslimov.vlad.taskmanager.mapper.TaskMapper;
import com.muslimov.vlad.taskmanager.model.Task;
import com.muslimov.vlad.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TaskService.class)
class TaskServiceTest {

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @Test
    void getAll() {

        var task1 = TaskDto.builder().id(1L).title("First task").build();
        var task2 = TaskDto.builder().id(2L).title("Second task").build();

        final List<TaskDto> expected = List.of(task1, task2);

        when(taskMapper.toDto(any()))
            .thenReturn(task1, task2);

        when(taskRepository.findAll())
            .thenReturn(List.of(new Task(), new Task()));

        final List<TaskDto> actual = taskService.getAll();

        assertEquals(expected, actual);
        verify(taskMapper, times(2)).toDto(any());
        verify(taskRepository).findAll();
    }

    @Test
    void getById() {

        var expected = TaskDto.builder().id(1L).title("Test task").build();

        when(taskMapper.toDto(any()))
            .thenReturn(expected);

        when(taskRepository.findByIdOrThrow(expected.getId()))
            .thenReturn(new Task());

        final TaskDto actual = taskService.getById(expected.getId());

        assertEquals(expected, actual);
        verify(taskMapper).toDto(any());
        verify(taskRepository).findByIdOrThrow(expected.getId());
    }

    @Test
    void createTask() {

        var expected = TaskDto.builder().build();
        final var task = new Task();

        when(taskMapper.toEntity(any()))
            .thenReturn(task);

        when(taskRepository.save(any()))
            .thenReturn(task);

        when(taskMapper.toDto(any()))
            .thenReturn(expected);

        final TaskDto actual = taskService.createTask(TaskCreateDto.builder().build());

        assertEquals(expected, actual);
        verify(taskMapper).toDto(any());
        verify(taskMapper).toEntity(any());
        verify(taskRepository).save(task);
    }

    @Test
    void updateTask() {

        var taskUpdateDto = TaskUpdateDto.builder().id(1L).build();
        var taskDto = TaskDto.builder().build();

        when(taskMapper.toDto(any()))
            .thenReturn(taskDto);

        when(taskRepository.findByIdOrThrow(1L))
            .thenReturn(new Task());

        assertEquals(taskDto, taskService.updateTask(taskUpdateDto));
        verify(taskMapper).toDto(any());
        verify(taskRepository).findByIdOrThrow(1L);
    }

    @Test
    void deleteTask() {

        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTask(1L);

        verify(taskRepository).deleteById(1L);
    }
}