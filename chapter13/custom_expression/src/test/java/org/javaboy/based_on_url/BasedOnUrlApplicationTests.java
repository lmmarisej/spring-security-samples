package org.javaboy.based_on_url;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BasedOnUrlApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext // 容器重启 避免缓存
    @WithMockUser(username = "2", password = "2")   // roles = "USER" 默认
    public void hello() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/hello"))
                .andExpect(status().isOk())
                .andExpect(matchAll(mvcResult -> {
                    assert mvcResult.getResponse().getContentAsString().equals("hello user");
                }));
    }

    @Test
    @DirtiesContext // 容器重启 避免缓存
    @WithMockUser(username = "1", password = "1", roles = "ADMIN")
    public void shouldReturnAllShoppingCarts() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/admin/hello"))
                .andExpect(status().isOk())
                .andExpect(matchAll(mvcResult -> {
                    assert mvcResult.getResponse().getContentAsString().equals("hello admin");
                }));
    }

}
