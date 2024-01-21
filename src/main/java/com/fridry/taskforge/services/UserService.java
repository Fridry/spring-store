package com.fridry.taskforge.services;

import com.fridry.taskforge.dtos.UserDTO;
import com.fridry.taskforge.dtos.UserResponse;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserResponse getAllUsers(int pageNumber, int pageSize);
    UserDTO getUser(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
