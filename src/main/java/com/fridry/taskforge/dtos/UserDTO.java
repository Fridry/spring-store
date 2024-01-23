package com.fridry.taskforge.dtos;


public record UserDTO
        (Long id,
         String name,
         String email,
         String password) {
}