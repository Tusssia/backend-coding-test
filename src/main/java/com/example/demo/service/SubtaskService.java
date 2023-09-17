package com.example.demo.service;

import com.example.demo.dto.SubtaskDTO;
import com.example.demo.mapper.SubtaskMapper;
import com.example.demo.model.SubtaskEntity;
import com.example.demo.model.TaskEntity;
import com.example.demo.repository.SubtaskRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SubtaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubtaskRepository subtaskRepository;
    public SubtaskDTO addSubtask(long parentTaskID, SubtaskDTO subtask) {
        TaskEntity parentTask = taskRepository.findById(parentTaskID)
                .orElseThrow((() -> new EntityNotFoundException("No parent task with id: " + parentTaskID + " exists")));

        SubtaskEntity subtaskEntity = SubtaskMapper.MAPPER.dtoToEntity(subtask);
        subtaskEntity.setParentTask(parentTask);
        subtaskRepository.save(subtaskEntity);
        return SubtaskMapper.MAPPER.entityToDto(subtaskEntity);
    }
}
