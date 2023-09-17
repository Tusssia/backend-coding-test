package com.example.demo.controller;

import com.example.demo.model.TaskEntity;
import com.example.demo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static com.example.demo.controller.DataHelper.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @MockBean
    TaskRepository taskRepository;

    @BeforeEach
    public void setUp(){
        prepareTestData();
    }

    @Test
    public void testGetAll() throws Exception {
        Page<TaskEntity> page = new PageImpl<>(tasks);
        when(taskRepository.findAll(PageRequest.of(0,10, Sort.by("id").ascending())))
                .thenReturn(page);
        mockMvc.perform(get("/api/task"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[5].id").value("6"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTaskById() throws Exception {
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(taskEntity1));
        mockMvc.perform(get("/api/task/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(status().isOk());
    }

}