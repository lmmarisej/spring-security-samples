package org.javaboy.ignoredrequests;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/9/17 3:06 下午
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(Authentication auth) {
        return "username: " + auth.getName() + "<br>roles: " + Arrays.toString(auth.getAuthorities().toArray());
    }

}
