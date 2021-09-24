package org.javaboy.http_basic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello http basic";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
