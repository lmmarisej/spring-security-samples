package org.javaboy.passwordencoder.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.javaboy.passwordencoder.model.User;


@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);

    Integer updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);
}
