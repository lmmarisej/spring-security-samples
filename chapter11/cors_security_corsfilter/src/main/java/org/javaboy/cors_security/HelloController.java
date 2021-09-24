package org.javaboy.cors_security;

import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {
    @PostMapping("/post")
    public String post() {
        return "hello post";
    }

    @PutMapping("/put")
    public String put() {
        return "hello put";
    }

    @GetMapping("/get")
    public String get() {
        return "hello get";
    }
}
