package org.javaboy.http_response_headers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hello.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL)))
                .and()
                .csrf().disable()
                .headers()
                .featurePolicy("vibrate 'none'; geolocation 'none'")
                .and()
                .referrerPolicy()
                .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.ORIGIN_WHEN_CROSS_ORIGIN)
                .and()
                .contentSecurityPolicy(contentSecurityPolicyConfig -> {
                    contentSecurityPolicyConfig.policyDirectives("default-src 'self'; script-src 'self'; object-src 'none';style-src cdn.lmmarise.org; img-src *; child-src https:;report-uri http://localhost:8081/report");
                    contentSecurityPolicyConfig.reportOnly();
                });

    }
}
