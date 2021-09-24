package org.javaboy.formlogin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.formlogin.model.Role;
import org.javaboy.formlogin.model.User;

import java.util.List;


@Mapper
public interface UserMapper {
    List<Role> getRolesByUid(Integer id);
    User loadUserByUsername(String username);
}
