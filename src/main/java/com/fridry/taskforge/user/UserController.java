package com.fridry.taskforge.user;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        User newUser = userRepository.save(user);

        String resourceUrl = "/users/" + newUser.getId();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", resourceUrl)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        user.setId(id);
        User newUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> partialUserUpdate(@PathVariable Long id, @RequestBody User userUpdates) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(userUpdates, user.get(), getNullPropertyNames(userUpdates));

        User newUser = userRepository.save(user.get());

        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }



    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    // Método para obter nomes de propriedades nulas
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
