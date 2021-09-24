package org.javaboy.auth_server_jwt;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @Autowired
    JWKSet jwkSet;

    @GetMapping(value = "/oauth2/keys")
    public String keys() {
        return jwkSet.toString();
    }
}