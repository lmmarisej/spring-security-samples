package org.javaboy.exception_translation_filter;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServletException.class)
    public void handle1(ServletException e) {
        System.out.println("e.getMessage() = " + e.getMessage());
    }
}
