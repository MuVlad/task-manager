package com.muslimov.vlad.taskmanager.repository;

import com.muslimov.vlad.taskmanager.exception.model.NotFoundException;
import com.muslimov.vlad.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    default Task findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new NotFoundException("Task with id: " + id + " not found")
        );
    }
}