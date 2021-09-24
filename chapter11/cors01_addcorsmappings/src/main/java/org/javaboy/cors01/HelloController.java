package org.javaboy.cors01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


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
