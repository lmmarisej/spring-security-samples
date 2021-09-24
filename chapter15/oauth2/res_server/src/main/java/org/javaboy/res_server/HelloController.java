package org.javaboy.res_server;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        return "hello res server[authentication = " + authentication + "]";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/hello";
    }
}
