package org.javaboy.exception_translation_filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AntPathRequestMatcher matcher1 = new AntPathRequestMatcher("/qq/**");
        AntPathRequestMatcher matcher2 = new AntPathRequestMatcher("/wx/**");
        http.authorizeRequests()
                .antMatchers("/wx/**").hasRole("wx")
                .antMatchers("/qq/**").hasRole("qq")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor((req, resp, e) -> {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                    resp.getWriter().write("请登录，QQ 用户");
                }, matcher1)
                .defaultAuthenticationEntryPointFor((req, resp, e) -> {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                    resp.getWriter().write("请登录，WX 用户");
                }, matcher2)
                .defaultAccessDeniedHandlerFor((req, resp, e) -> {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.setStatus(HttpStatus.FORBIDDEN.value());
                    resp.getWriter().write("权限不足，QQ 用户");
                }, matcher1)
                .defaultAccessDeniedHandlerFor((req, resp, e) -> {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.setStatus(HttpStatus.FORBIDDEN.value());
                    resp.getWriter().write("权限不足，WX 用户");
                }, matcher2)
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }
}
