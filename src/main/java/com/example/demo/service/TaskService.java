package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.TaskEntity;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDTO> getAll() {
        List<TaskEntity> taskList = taskRepository.findAll();
        return TaskMapper.MAPPER.entityListToDtoList(taskList);
    }

    public TaskDTO getTaskById(long taskID) {
        TaskEntity task = taskRepository.findById(taskID).orElse(null);
        return TaskMapper.MAPPER.entityToDto(task);
    }

    public TaskDTO createTask(TaskDTO task) {
        TaskEntity taskEntity = TaskMapper.MAPPER.dtoToEntity(task);
        taskRepository.save(taskEntity);
        return TaskMapper.MAPPER.entityToDto(taskEntity);
    }

    public TaskDTO updateTask(long taskID, TaskDTO task) {
        TaskEntity taskEntity = taskRepository.findById(taskID).orElse(null);
        if (taskEntity != null) {
            TaskEntity updatedTaskEntity = TaskMapper.MAPPER.dtoToEntity(task);
            taskRepository.save(updatedTaskEntity);
        }
        //fix
        return task;
    }

    public void deleteTask(long taskID) {
        taskRepository.deleteById(taskID);
    }
}
