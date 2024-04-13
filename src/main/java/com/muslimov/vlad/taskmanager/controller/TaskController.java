package com.muslimov.vlad.taskmanager.controller;

import com.muslimov.vlad.taskmanager.dto.ResponseMessage;
import com.muslimov.vlad.taskmanager.dto.TaskCreateDto;
import com.muslimov.vlad.taskmanager.dto.TaskDto;
import com.muslimov.vlad.taskmanager.dto.TaskUpdateDto;
import com.muslimov.vlad.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@Tag(name = "Задачи", description = "Контроллер для работы с задачами")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Получение списка задач")
    public HttpEntity<List<TaskDto>> getTasks() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение задачи по id")
    public HttpEntity<TaskDto> getTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Создание новой задачи")
    public HttpEntity<TaskDto> createTask(@RequestBody @Valid TaskCreateDto taskCreateDto) {
        return ResponseEntity.ok(taskService.createTask(taskCreateDto));
    }

    @PutMapping()
    @Operation(summary = "Обновление задачи")
    public HttpEntity<TaskDto> updateTask(@RequestBody @Valid TaskUpdateDto taskUpdateDto) {
        return ResponseEntity.ok(taskService.updateTask(taskUpdateDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление задачи")
    public HttpEntity<ResponseMessage> deleteTask(@PathVariable("id") Long id) {

        taskService.deleteTask(id);
        return ResponseEntity.ok(
            new ResponseMessage("Task deleted successfully")
        );
    }
}