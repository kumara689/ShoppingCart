package org.kithsiri.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kithsiri.rest.repo.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ShoppingCartRepo shoppingCartRepo;
    
    
    @Test
    public void testFetchShoppingCartById() throws Exception {
        String shoppingCartId = "1";

        mockMvc.perform(post("/cart").contentType(MediaType.ALL_VALUE).content(shoppingCartId)).andExpect(status().isBadRequest());
    }
    
}
