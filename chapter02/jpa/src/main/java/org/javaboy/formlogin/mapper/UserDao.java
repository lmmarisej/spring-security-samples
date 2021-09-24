package org.javaboy.formlogin.mapper;

import org.javaboy.formlogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}