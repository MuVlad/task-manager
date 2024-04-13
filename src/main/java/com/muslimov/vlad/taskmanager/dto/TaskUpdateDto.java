package com.muslimov.vlad.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateDto {

    @NotNull
    private Long id;
    private String title;
    private String description;
    private boolean completed;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;
}
