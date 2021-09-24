package org.javaboy.formlogin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/mylogin.html")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/index.html")
                .failureHandler(new MyAuthenticationFailureHandler())
                .usernameParameter("uname")
                .passwordParameter("passwd")
                .permitAll()
                .and()
                .logout()   // 开启注销登录配置
                .logoutRequestMatcher(new OrRequestMatcher(     // 访问以下匹配器匹配上的URL，都进行注销处理
                        new AntPathRequestMatcher("/logout", "GET"),
                        new AntPathRequestMatcher("/logout1", "GET"),
                        new AntPathRequestMatcher("/logout2", "POST")))
                .invalidateHttpSession(true)    // 使Session失效
                .clearAuthentication(true)      // 清除认证信息
                .logoutSuccessUrl("/mylogin.html")  // 默认注销成功跳转地址【适合前后端不分离】
                .defaultLogoutSuccessHandlerFor((req, resp, auth) -> {  // 注销成功处理器【适合前后端分离】
                    resp.setContentType("application/json;charset=utf-8");
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", 200);
                    result.put("msg", "使用 logout1 注销成功!");
                    ObjectMapper om = new ObjectMapper();
                    String s = om.writeValueAsString(result);
                    resp.getWriter().write(s);
                }, new AntPathRequestMatcher("/logout1", "GET"))    // 匹配注销地址
                .defaultLogoutSuccessHandlerFor((req, resp, auth) -> {  // 可以注册多个注销登录回调函数，给出不同的响应信息
                    resp.setContentType("application/json;charset=utf-8");
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", 200);
                    result.put("msg", "使用 logout2 注销成功!");
                    ObjectMapper om = new ObjectMapper();
                    String s = om.writeValueAsString(result);
                    resp.getWriter().write(s);
                }, new AntPathRequestMatcher("/logout2", "POST"))
                .and()
                .csrf().disable();
    }
}
