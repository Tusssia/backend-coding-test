package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.TaskEntity;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDTO> getAll(PageRequest pageRequest) {
        Page<TaskEntity> page = taskRepository.findAll(pageRequest);
        List<TaskEntity> taskList = page.getContent();
        return TaskMapper.MAPPER.entityListToDtoList(taskList);
    }

    public List<TaskDTO> getAllByDescription(PageRequest pageRequest, String description) {
        Page<TaskEntity> page = taskRepository.findByDescriptionContainingIgnoreCase(description, pageRequest);
        List<TaskEntity> taskList = page.getContent();
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
        TaskEntity taskEntity = taskRepository.findById(taskID)
                .orElseThrow(() -> new EntityNotFoundException("Task with id: " + taskID + " does not exist"));
        TaskEntity updatedTaskEntity = TaskMapper.MAPPER.dtoToEntity(task);

        taskEntity.setDescription(updatedTaskEntity.getDescription());
        taskEntity.setCompleted(updatedTaskEntity.isCompleted());
        taskEntity.setPriority(updatedTaskEntity.getPriority());

        taskRepository.save(taskEntity);
        return TaskMapper.MAPPER.entityToDto(taskEntity);
    }

    public void deleteTask(long taskID) {
        taskRepository.deleteById(taskID);
    }

}
