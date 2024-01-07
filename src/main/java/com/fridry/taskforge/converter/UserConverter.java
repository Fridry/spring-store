package com.fridry.taskforge.converter;

import com.fridry.taskforge.dtos.UserDTO;
import com.fridry.taskforge.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public static User convertToEntity(UserDTO userDTO) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        return modelMapper.map(userDTO, User.class);
    }
}

