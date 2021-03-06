package org.javaboy.client02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Controller
public class HelloController {
    @Autowired
    WebClient webClient;

    @GetMapping(value = "/authorize", params = "grant_type=authorization_code")
    public String authorization_code_grant(Model model) {
        String msg = retrieveMessages("auth-code");
        model.addAttribute("msg", msg);
        return "index";
    }

    @GetMapping(value = "/authorize", params = "grant_type=client_credentials")
    public String client_credentials_grant(Model model) {
        String msg = retrieveMessages("client-creds");
        model.addAttribute("msg", msg);
        return "index";
    }

    @PostMapping(value = "/authorize", params = "grant_type=password")
    public String password_grant(Model model) {
        String msg = retrieveMessages("password");
        model.addAttribute("msg", msg);
        return "index";
    }

    private String retrieveMessages(String clientRegistrationId) {
        String helloUri = "http://127.0.0.1:8882/hello";
        return webClient
                .get()
                .uri(helloUri)
                .attributes(clientRegistrationId(clientRegistrationId))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @ResponseBody
    @GetMapping("/login/oauth2/code")
    public String code(String code) {
        return code;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/token")
    @ResponseBody
    public String token(@RegisteredOAuth2AuthorizedClient("auth-code") OAuth2AuthorizedClient authorizedClient) {
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        return accessToken.getTokenValue();
    }

}
