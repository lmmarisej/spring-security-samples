package org.javaboy.oauth2_login_demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthorizationCodeAuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HelloController {
    @GetMapping("/")
    public DefaultOAuth2User index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((DefaultOAuth2User) authentication.getPrincipal());
    }
}