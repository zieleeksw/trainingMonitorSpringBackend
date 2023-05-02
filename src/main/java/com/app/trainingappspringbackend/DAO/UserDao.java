package com.app.trainingappspringbackend.DAO;

import com.app.trainingappspringbackend.POJO.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByEmail(String email);
}
