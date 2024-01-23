package com.fridry.taskforge.converter;

import com.fridry.taskforge.dtos.TaskDTO;
import com.fridry.taskforge.dtos.UserDTO;
import com.fridry.taskforge.entities.Task;
import com.fridry.taskforge.entities.User;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    public static TaskDTO mapToDto(Task task) {
        TaskDTO taskDTO = new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate()
        );

        return taskDTO;
    }

    public static Task mapToEntity(TaskDTO taskDTO) {
        Task task = new Task();

        task.setId(taskDTO.id());
        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setStatus(taskDTO.status());
        task.setDueDate(taskDTO.dueDate());

        return task;
    }
}
