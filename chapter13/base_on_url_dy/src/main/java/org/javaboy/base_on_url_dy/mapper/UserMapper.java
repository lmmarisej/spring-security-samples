package org.javaboy.base_on_url_dy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.base_on_url_dy.model.Menu;
import org.javaboy.base_on_url_dy.model.Role;
import org.javaboy.base_on_url_dy.model.User;

import java.util.List;


@Mapper
public interface UserMapper {
    List<Role> getUserRoleByUid(Integer uid);

    User loadUserByUsername(String username);
}
