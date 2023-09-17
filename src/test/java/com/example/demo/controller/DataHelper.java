package com.example.demo.controller;

import com.example.demo.model.TaskEntity;
import com.example.demo.model.TaskPriority;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    static TaskEntity taskEntity1 = new TaskEntity(1, "First task", true, TaskPriority.HIGH, null);
    static TaskEntity taskEntity2 = new TaskEntity(2, "First task", true, TaskPriority.HIGH, null);
    static TaskEntity taskEntity3 = new TaskEntity(3, "First task", true, TaskPriority.HIGH, null);
    static TaskEntity taskEntity4 = new TaskEntity(4, "First task", true, TaskPriority.HIGH, null);
    static TaskEntity taskEntity5 = new TaskEntity(5, "First task", true, TaskPriority.HIGH, null);
    static TaskEntity taskEntity6 = new TaskEntity(6, "First task", true, TaskPriority.HIGH, null);

    static List<TaskEntity> tasks = new ArrayList<>();

    static void prepareTestData() {
        tasks.clear();
        tasks.add(taskEntity1);
        tasks.add(taskEntity2);
        tasks.add(taskEntity3);
        tasks.add(taskEntity4);
        tasks.add(taskEntity5);
        tasks.add(taskEntity6);
    }
}
