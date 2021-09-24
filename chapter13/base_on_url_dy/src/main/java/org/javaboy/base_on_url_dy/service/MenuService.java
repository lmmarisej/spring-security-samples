package org.javaboy.base_on_url_dy.service;

import org.javaboy.base_on_url_dy.mapper.MenuMapper;
import org.javaboy.base_on_url_dy.mapper.UserMapper;
import org.javaboy.base_on_url_dy.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }
}
