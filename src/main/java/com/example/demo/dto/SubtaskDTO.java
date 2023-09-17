package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubtaskDTO {

    private long id;

    private String description;

    private boolean completed;

    private TaskDTO parentTask;
}
