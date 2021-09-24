package org.javaboy.exception_translation_filter;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() throws Exception {
//        throw new ServletException("aaa");
        return "hello";
//        throw new AuthenticationServiceException("bbb");
    }
}
