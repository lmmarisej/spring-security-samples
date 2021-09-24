package org.javaboy.base_on_url_dy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.javaboy.base_on_url_dy.model.Menu;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getAllMenu();
}
