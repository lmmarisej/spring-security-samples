package org.javaboy.formlogin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class UserController {
    @GetMapping("/user")
    public void userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("name = " + name);
        System.out.println("authorities = " + authorities);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication == null) {
                    System.out.println("子线程「" + threadName + "」获取用户信息失败!");
                    return;
                }
                String name = authentication.getName();
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                System.out.println("子线程「" + threadName + "」获取用户信息成功：");
                System.out.println("\t" + threadName + ":name = " + name);
                System.out.println("\t" + threadName + ":authorities = " + authorities);
            }
        }).start();
    }
}
