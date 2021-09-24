package org.javaboy.multi_filter_chain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/userinfo")
    public String userinfo(Authentication authentication) {
        if (authentication == null) return "未授权";
        try {
            return new ObjectMapper().writeValueAsString(authentication);
        } catch (JsonProcessingException e) {
            return String.valueOf(authentication);
        }
    }

    @GetMapping("/bar/userinfo")
    public String barUserinfo(Authentication authentication) {
        if (authentication == null) return "未授权";
        try {
            return new ObjectMapper().writeValueAsString(authentication);
        } catch (JsonProcessingException e) {
            return String.valueOf(authentication);
        }
    }

    @GetMapping("/foo/userinfo")
    public String fooUserinfo(Authentication authentication) {
        if (authentication == null) return "未授权";
        try {
            return new ObjectMapper().writeValueAsString(authentication);
        } catch (JsonProcessingException e) {
            return String.valueOf(authentication);
        }
    }
}