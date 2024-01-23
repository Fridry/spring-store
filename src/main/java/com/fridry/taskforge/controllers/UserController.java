package com.fridry.taskforge.controllers;


import com.fridry.taskforge.dtos.UserDTO;
import com.fridry.taskforge.dtos.UserResponse;
import com.fridry.taskforge.entities.User;
import com.fridry.taskforge.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = new UserDTO(
                userDTO.id(),
                userDTO.name(),
                userDTO.email(),
                userDTO.password()
        );

        UserDTO savedUser = userService.createUser(newUser);

        String resourceUrl = "/users/" + savedUser.id();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", resourceUrl)
                .body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> partialUpdateUser(@PathVariable Long id, @RequestBody User user) {
        UserDTO userDTO = userService.getUser(id);

        BeanUtils.copyProperties(user, userDTO, getNullPropertyNames(user));

        UserDTO updatedUser = userService.updateUser(id, userDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private String[] getNullPropertyNames(User user) {
        final BeanWrapper src = new BeanWrapperImpl(user);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        return emptyNames.toArray(new String[0]);
    }
}
