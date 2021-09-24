package org.javaboy.rememberme;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("SecurityContextHolder.getContext().getAuthentication().getClass() = " + SecurityContextHolder.getContext().getAuthentication().getClass());
        return "hello";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/rememberMe")
    public String rememberMe() {
        return "rememberMe";
    }
}
