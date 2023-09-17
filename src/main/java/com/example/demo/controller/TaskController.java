package com.example.demo.controller;

import com.example.demo.dto.TaskDTO;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<List<TaskDTO>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("{taskID}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("taskID") long taskID) {
        return ResponseEntity.ok(taskService.getTaskById(taskID));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("{taskID}")
    public ResponseEntity<TaskDTO> update(@PathVariable("taskID") long taskID, @RequestBody TaskDTO task) {
        return ResponseEntity.ok(taskService.updateTask(taskID, task));
    }

    @DeleteMapping("{taskID}")
    public void delete(@PathVariable("taskID") long taskID) {
        taskService.deleteTask(taskID);
    }

}
