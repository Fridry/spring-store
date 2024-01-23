package com.fridry.taskforge.dtos;

import java.util.Date;

public record TaskDTO(Long id, String title, String description, String status, Date dueDate) {
}