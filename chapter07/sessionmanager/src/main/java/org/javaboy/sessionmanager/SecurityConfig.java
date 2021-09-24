package org.javaboy.sessionmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("1").password("{noop}1").roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionFixation().changeSessionId()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation()
                .none()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(event -> {
                    HttpServletResponse response = event.getResponse();
                    response.setContentType("application/json;charset=utf-8");
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", 500);
                    result.put("msg", "当前会话已经失效，请重新登录");
                    String s = new ObjectMapper().writeValueAsString(result);
                    response.getWriter().print(s);
                    response.flushBuffer();
                });
    }

    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
