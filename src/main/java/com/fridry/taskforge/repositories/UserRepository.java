package com.fridry.taskforge.repositories;

import com.fridry.taskforge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
}
