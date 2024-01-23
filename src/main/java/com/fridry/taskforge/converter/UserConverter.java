package com.fridry.taskforge.converter;

import com.fridry.taskforge.dtos.UserDTO;
import com.fridry.taskforge.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public static UserDTO mapToDto(User user) {
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword());

        return userDTO;
    }

    public static User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.id());
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());

        return user;
    }
}

