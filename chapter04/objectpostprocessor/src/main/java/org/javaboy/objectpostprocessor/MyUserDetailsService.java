package org.javaboy.objectpostprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/9/17 12:43 上午
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, "{noop}1", new AbstractCollection<GrantedAuthority>() {
            @Override
            public Iterator<GrantedAuthority> iterator() {
                return new Iterator<GrantedAuthority>() {
                    boolean b = true;

                    @Override
                    public boolean hasNext() {
                        return b;
                    }

                    @Override
                    public GrantedAuthority next() {
                        b = false;
                        return () -> "user";
                    }
                };
            }

            @Override
            public int size() {
                return 1;
            }
        });
    }
}
