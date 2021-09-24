package org.javaboy.res_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.resource-server.opaque.introspection-uri}")
    String introspectionUri;
    @Value("${spring.security.oauth2.resource-server.opaque.introspection-client-id}")
    String clientId;
    @Value("${spring.security.oauth2.resource-server.opaque.introspection-client-secret}")
    String clientSecret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().opaqueToken()
                .introspectionUri(introspectionUri)
                .introspectionClientCredentials(clientId, clientSecret);
    }
}