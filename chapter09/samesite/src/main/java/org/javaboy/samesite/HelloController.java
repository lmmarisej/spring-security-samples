package org.javaboy.samesite;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class HelloController {
    @PostMapping("/withdraw")
    public void withdraw() {
        System.out.println("执行了一次转账操作");
    }

    @GetMapping("/hello")
    public void hello(HttpSession session) {
        System.out.println("session = " + session);
    }
}
