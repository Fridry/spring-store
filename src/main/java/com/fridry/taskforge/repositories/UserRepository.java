package com.fridry.taskforge.repositories;

import com.fridry.taskforge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
