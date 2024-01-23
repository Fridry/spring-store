package com.fridry.taskforge.services;

import com.fridry.taskforge.converter.TaskConverter;
import com.fridry.taskforge.dtos.TaskDTO;
import com.fridry.taskforge.dtos.TaskResponse;
import com.fridry.taskforge.entities.Task;
import com.fridry.taskforge.entities.User;
import com.fridry.taskforge.exceptions.TaskNotFoundException;
import com.fridry.taskforge.exceptions.UserNotFoundException;
import com.fridry.taskforge.repositories.TaskRepository;
import com.fridry.taskforge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private TaskConverter taskConverter;

    @Autowired
    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository, TaskConverter taskConverter) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
    }

    @Override
    public TaskDTO createTask(Long userId, TaskDTO taskDTO) {
        Task task = taskConverter.mapToEntity(taskDTO);

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated task not found"));

        task.setUser(user);
        Task newTask = taskRepository.save(task);

        return taskConverter.mapToDto(newTask);
    }

    @Override
    public TaskResponse getTasksByUserId(Long userId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Task> tasks = taskRepository.findByUserId(userId, pageable);

        List<Task> taskList = tasks.getContent();
        List<TaskDTO> content = taskList.stream().map(task -> taskConverter.mapToDto(task)).collect(Collectors.toList());

        TaskResponse taskResponse = new TaskResponse(
                content,
                tasks.getNumber(),
                tasks.getSize(),
                tasks.getTotalElements(),
                tasks.getTotalPages(),
                tasks.isLast()
        );

        return taskResponse;
    }

    @Override
    public TaskDTO getTaskById(Long taskId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated task not found"));

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task with associated user not found"));

        if(!task.getUser().getId().equals(user.getId())) {
            throw new TaskNotFoundException("Task with associated user not found");
        }

        return taskConverter.mapToDto(task);
    }

    @Override
    public TaskDTO updateTask(Long userId, Long taskId, TaskDTO taskDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated task not found"));

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task with associated user not found"));

        if(!task.getUser().getId().equals(user.getId())) {
            throw new TaskNotFoundException("Task with associated user not found");
        }

        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setStatus(taskDTO.status());
        task.setDueDate(taskDTO.dueDate());

        Task updatedTask = taskRepository.save(task);

        return taskConverter.mapToDto(updatedTask);
    }

    @Override
    public void deleteTask(Long userId, Long taskId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with associated task not found"));

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task with associated user not found"));

        if(!task.getUser().getId().equals(user.getId())) {
            throw new TaskNotFoundException("Task with associated user not found");
        }

        taskRepository.delete(task);
    }
}
