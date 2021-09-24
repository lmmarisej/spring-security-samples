package tsb;

import org.springframework.context.annotation.Bean;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/9/22 1:49 下午
 */
public class A {
    @Bean
    public B b() {
        return new B();
    }
}
