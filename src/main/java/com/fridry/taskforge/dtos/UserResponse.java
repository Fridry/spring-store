package com.fridry.taskforge.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private List<UserDTO> content;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
