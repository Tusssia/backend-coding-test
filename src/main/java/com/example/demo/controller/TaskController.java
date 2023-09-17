package com.example.demo.controller;

import com.example.demo.dto.SubtaskDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.service.SubtaskService;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private SubtaskService subtaskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll(@RequestParam(name = "sort", defaultValue = "id") String sort,
                                                @RequestParam(name = "direction", defaultValue =  "asc") String direction,
                                                @RequestParam(required = false) String description) {
        PageRequest pageRequest = PageRequest.of(0, 10, direction.equals("asc") ? Sort.by(sort).ascending()
                : Sort.by(sort).descending());
        if (description == null) {
            return ResponseEntity.ok(taskService.getAll(pageRequest));
        } else {
            return ResponseEntity.ok(taskService.getAllByDescription(pageRequest, description));
        }
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

    @PostMapping("{parentTaskID}/subtask")
    public ResponseEntity<SubtaskDTO> addSubtask(@PathVariable("parentTaskID") long parentTaskID,
                                        @RequestBody SubtaskDTO subtask) {
        return ResponseEntity.ok(subtaskService.addSubtask(parentTaskID, subtask));
    }
}
