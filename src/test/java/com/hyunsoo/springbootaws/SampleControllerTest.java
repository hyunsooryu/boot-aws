package com.hyunsoo.springbootaws;


import com.hyunsoo.springbootaws.controller.SampleController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class SampleControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public SampleControllerTest(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }

    @Test
    void returnHello() throws Exception{
        String ans = "Hello";
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.is(ans)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void returnHelloResponseDto() throws Exception{
        String name = "Hello";
        int amount = 1000;

        MultiValueMap<String,String> params =
                new LinkedMultiValueMap<>();
        params.add("name", name);
        params.add("amount", String.valueOf(amount));
        mockMvc.perform(get("/hello/dto").params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(name)))
                .andExpect(jsonPath("$.amount", Matchers.is(amount)))
                .andDo(MockMvcResultHandlers.print());
    }




}
