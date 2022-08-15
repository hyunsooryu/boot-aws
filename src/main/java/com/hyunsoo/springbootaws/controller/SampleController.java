package com.hyunsoo.springbootaws.controller;


import com.hyunsoo.springbootaws.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {
    @GetMapping("")
    public String getHello(){
        return "Hello";
    }

    @GetMapping("/dto")
    public HelloResponseDto getHelloResponseDto(@RequestParam("name") String name,
                                        @RequestParam("amount") int amount){

        return new HelloResponseDto(name, amount);
    }
}
