package org.javaboy.synchronizer_token_pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HelloController {
    @PostMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello csrf！";
    }

    @GetMapping("/index.html")
    public String index() {
        return "index";
    }
    @GetMapping("/index2.html")
    public String index2() {
        return "index2";
    }
}