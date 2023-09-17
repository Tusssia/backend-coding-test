package com.example.demo.dto;

import com.example.demo.model.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private long id;

    private String description;

    private boolean completed;

    private TaskPriority priority;

}
