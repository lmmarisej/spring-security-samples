package org.javaboy.objectpostprocessor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @GetMapping("/userinfo")
    public String hello(Authentication authentication) {
        return authentication.getPrincipal().toString();
    }
}
