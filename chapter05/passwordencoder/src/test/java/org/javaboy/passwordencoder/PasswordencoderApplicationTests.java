package org.javaboy.passwordencoder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class PasswordencoderApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
        System.out.println(encoder.encode("1"));
    }

}