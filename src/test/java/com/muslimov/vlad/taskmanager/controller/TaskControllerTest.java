package com.muslimov.vlad.taskmanager.controller;

import com.muslimov.vlad.taskmanager.dto.TaskUpdateDto;
import com.muslimov.vlad.taskmanager.model.Task;
import com.muslimov.vlad.taskmanager.support.BaseIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest extends BaseIntegrationTest {

    @AfterEach
    public void resetDB() {
        taskRepository.deleteAll();
    }

    @Test
    @DisplayName("Получение списка задач")
    void getTasks() throws Exception {

        final var task1 = savingTaskToDB("Test name");
        final var task2 = savingTaskToDB("Other name");

        mockMvc.perform(get("/api/v1/tasks"))
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(objectMapper.writeValueAsString(Arrays.asList(task1, task2)))
            );
    }

    @Test
    @DisplayName("получение задачи по id")
    void getTaskById() throws Exception {

        final var task = savingTaskToDB("Test name");

        mockMvc.perform(get("/api/v1/tasks/{id}", task.getId()))
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json(objectMapper.writeValueAsString(task))
            );
    }

    @Test
    @DisplayName("Обнавление задачи")
    void updateTask() throws Exception {

        final var oldTask = savingTaskToDB("Test name");
        var newTask = TaskUpdateDto.builder()
            .id(oldTask.getId())
            .title("Other name")
            .build();

        mockMvc.perform(put("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTask))
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(newTask.getId()))
            .andExpect(jsonPath("$.title").value(newTask.getTitle()));
    }

    @Test
    @DisplayName("Удаление задачи")
    void deleteTask() throws Exception {

        final var task = savingTaskToDB("Test name");
        String responseMessage = "{\"message\":\"Task deleted successfully\"}";

        mockMvc.perform(delete("/api/v1/tasks/{id}", task.getId()))
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().string(responseMessage)
            );
        assertFalse(taskRepository.findById(task.getId()).isPresent());
    }

    private Task savingTaskToDB(String name) {

        return taskRepository.save(
            Task.builder()
                .title(name)
                .description("Test description")
                .dueDate(LocalDate.of(2024, Month.APRIL, 7))
                .completed(false)
                .build()
        );
    }
}