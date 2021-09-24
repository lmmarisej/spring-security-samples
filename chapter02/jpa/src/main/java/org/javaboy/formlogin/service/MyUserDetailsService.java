package org.javaboy.formlogin.service;

import org.javaboy.formlogin.mapper.UserDao;
import org.javaboy.formlogin.model.Role;
import org.javaboy.formlogin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    public boolean userExists(String username) {
        return userDao.findUserByUsername(username) != null;
    }

    public void createUser(UserDetails admin) {
        User user = new User();
        user.setRoles(authoritiesToRoles(admin.getAuthorities()));
        user.setUsername(admin.getUsername());
        user.setPassword(admin.getPassword());
        userDao.save(user);
    }

    private Collection<Role> authoritiesToRoles(Collection<? extends GrantedAuthority> authorities) {
        ArrayList<Role> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            roles.add(new Role(authority.getAuthority()));
        }
        return roles;
    }


}
