package com.app.trainingappspringbackend.DAO;

import com.app.trainingappspringbackend.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(@Param("email") String email);
}
