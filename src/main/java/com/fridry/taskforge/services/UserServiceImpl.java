package com.fridry.taskforge.services;

import com.fridry.taskforge.converter.UserConverter;
import com.fridry.taskforge.dtos.UserDTO;
import com.fridry.taskforge.dtos.UserResponse;
import com.fridry.taskforge.entities.User;
import com.fridry.taskforge.exceptions.UserNotFoundException;
import com.fridry.taskforge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userConverter.mapToEntity(userDTO);

        User newUser = userRepository.save(user);

        UserDTO userResponse = userConverter.mapToDto(newUser);

        return userResponse;
    }

    @Override
    public UserResponse getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<User> users = userRepository.findAll(pageable);

        List<User> usersList = users.getContent();
        List<UserDTO> content = usersList.stream().map(user -> userConverter.mapToDto(user)).collect(Collectors.toList());

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(content);
        userResponse.setPage(users.getNumber());
        userResponse.setSize(users.getSize());
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setLast(users.isLast());

        return userResponse;
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userConverter.mapToDto(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());

        User updatedUser = userRepository.save(user);

        return userConverter.mapToDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }
}
