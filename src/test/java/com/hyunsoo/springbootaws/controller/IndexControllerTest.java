package com.hyunsoo.springbootaws.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {IndexController.class})
public class IndexControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void indexControllerTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Spring Boot is Cool")))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        String html = result.getResponse().getContentAsString();
        System.out.println(html);



    }

}
