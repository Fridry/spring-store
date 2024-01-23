package com.fridry.taskforge.controllers;

import com.fridry.taskforge.dtos.TaskDTO;
import com.fridry.taskforge.dtos.TaskResponse;
import com.fridry.taskforge.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{userId}/task")
    public ResponseEntity<TaskResponse> getAllTasks(
            @PathVariable(required = true) Long userId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(taskService.getTasksByUserId(userId, page, size));
    }

    @GetMapping("/{userId}/task/{taskId}")
    public ResponseEntity<TaskDTO> getTask(
            @PathVariable Long userId,
            @PathVariable Long taskId
    ) {
        TaskDTO task = taskService.getTaskById(taskId, userId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/{userId}/task")
    public ResponseEntity<TaskDTO> createTask(
            @PathVariable Long userId,
            @RequestBody TaskDTO taskDTO
    ) {
        return new ResponseEntity<>(taskService.createTask(userId, taskDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/task/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable Long userId,
            @PathVariable Long taskId,
            @RequestBody TaskDTO taskDTO
    ) {
        return new ResponseEntity<>(taskService.updateTask(userId, taskId, taskDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/task/{taskId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTask(userId, taskId);
        return ResponseEntity.noContent().build();
    }
}
