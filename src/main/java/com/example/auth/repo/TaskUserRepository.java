package com.example.auth.repo;
import com.example.auth.entity.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskUserRepository extends JpaRepository<TaskUser, Integer> {
    Optional<TaskUser> findByEmail(String email);
    Optional<TaskUser> findByUserName(String username);
}
