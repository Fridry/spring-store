package com.fridry.taskforge.services;

import com.fridry.taskforge.dtos.TaskDTO;
import com.fridry.taskforge.dtos.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(Long userId, TaskDTO taskDTO);
    TaskResponse getTasksByUserId(Long userId, int pageNumber, int pageSize);
    TaskDTO getTaskById(Long taskId, Long userId);
    TaskDTO updateTask(Long userId, Long taskId, TaskDTO taskDTO);
    void deleteTask(Long userId, Long taskId);
}
