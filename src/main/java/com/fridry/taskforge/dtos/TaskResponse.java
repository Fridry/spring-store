package com.fridry.taskforge.dtos;

import java.util.List;

public record TaskResponse(
        List<TaskDTO> content,
        int page,
        int size,
        Long totalElements,
        int totalPages,
        boolean last
) {
}
