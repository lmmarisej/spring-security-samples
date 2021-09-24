package org.javaboy.cors01;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8081",
        allowedHeaders = "*",
        methods = {RequestMethod.POST},
        maxAge = 60 * 30,
        exposedHeaders = {"Access-Control-Request-Headers", "Access-Control-Request-Method"})
public class HelloController {
    @PostMapping("/post")
    public String post() {
        return "hello post";
    }

    @CrossOrigin(origins = "http://localhost:8081",
            allowedHeaders = "*",
            methods = {RequestMethod.PUT, RequestMethod.OPTIONS},
            maxAge = 60 * 30,
            exposedHeaders = {"Access-Control-Max-Age"})
    @PutMapping("/put")
    public String put() {
        return "hello put";
    }

    @GetMapping("/get")
    public String get() {
        return "hello get";
    }
}
