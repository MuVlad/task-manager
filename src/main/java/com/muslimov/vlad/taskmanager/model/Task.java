package com.muslimov.vlad.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tasks")
public class Task {

    private static final String SEQ_NAME = "task_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(
        name = SEQ_NAME,
        sequenceName = SEQ_NAME,
        allocationSize = 1
    )
    private Long id;
    private String title;
    private String description;
    private boolean completed;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;
}