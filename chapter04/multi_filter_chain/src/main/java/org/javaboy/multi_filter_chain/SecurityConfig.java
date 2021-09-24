package org.javaboy.multi_filter_chain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class SecurityConfig {
    @Bean
    UserDetailsService us() {
        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
        users.createUser(User.withUsername("1").password("{noop}1").roles("Spring_global_admin").build());
        return users;
    }

    /**
     * {@link SecurityConfig01} 的全局的AuthenticationManager将从容器中获取。
     */
    @Configuration
    @Order(1)
    static class SecurityConfig01 extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
            users.createUser(User.withUsername("2").password("{noop}2").roles("SecurityConfig01_local_user").build());
            http.antMatcher("/bar/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/bar/login")
                    .successHandler((req, resp, auth) -> {
                        resp.setContentType("application/json;charset=utf-8");
                        String s = new ObjectMapper().writeValueAsString(auth);
                        resp.getWriter().write(s);
                    })
                    .failureHandler((req, resp, authException) -> {
                        resp.setContentType("application/json;charset=utf-8");
                        String s = new ObjectMapper().writeValueAsString(authException);
                        resp.getWriter().write(s);
                    })
                    .permitAll()
                    .and()
                    .csrf().disable()
                    .userDetailsService(users);
        }
    }

    @Configuration
    @Order(2)
    static class SecurityConfig02 extends WebSecurityConfigurerAdapter {
        /**
         * {@link SecurityConfig01#configure(AuthenticationManagerBuilder)} 重写后不再使用
         * {@link AuthenticationConfiguration#getAuthenticationManager()} 获取全局的 AuthenticationManager认证用户。
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("3")
                    .password("{noop}3")
                    .roles("SecurityConfig02_global_admin");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
            users.createUser(User.withUsername("4").password("{noop}4").roles("SecurityConfig02_local_user").build());
            http.antMatcher("/foo/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/foo/login")
                    .successHandler((req, resp, auth) -> {
                        resp.setContentType("application/json;charset=utf-8");
                        String s = new ObjectMapper().writeValueAsString(auth);
                        resp.getWriter().write(s);
                    })
                    .failureHandler((req, resp, authException) -> {
                        resp.setContentType("application/json;charset=utf-8");
                        String s = new ObjectMapper().writeValueAsString(authException);
                        resp.getWriter().write(s);
                    })
                    .permitAll()
                    .and()
                    .csrf().disable()
                    .userDetailsService(users);
        }
    }

}
