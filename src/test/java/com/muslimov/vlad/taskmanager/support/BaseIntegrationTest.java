package com.muslimov.vlad.taskmanager.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muslimov.vlad.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected TaskRepository taskRepository;

    @Autowired
    protected ObjectMapper objectMapper;
}
