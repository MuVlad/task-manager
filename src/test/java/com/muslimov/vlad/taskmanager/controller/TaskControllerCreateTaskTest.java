package com.muslimov.vlad.taskmanager.controller;

import com.muslimov.vlad.taskmanager.dto.TaskCreateDto;
import com.muslimov.vlad.taskmanager.support.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TaskControllerCreateTaskTest extends BaseIntegrationTest {

    @Test
    @DisplayName("Создание задачи")
    void createTask() throws Exception {

        final var task = TaskCreateDto.builder()
            .title("Test name")
            .description("Test description")
            .dueDate(LocalDate.of(2024, Month.APRIL, 7))
            .completed(false)
            .build();

        mockMvc.perform(post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task))
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.title").value(task.getTitle()))
            .andExpect(jsonPath("$.description").value(task.getDescription()));
    }
}
