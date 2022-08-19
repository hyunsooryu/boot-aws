package com.hyunsoo.springbootaws.controller;

import com.hyunsoo.springbootaws.dto.PostsResponseDto;
import com.hyunsoo.springbootaws.dto.PostsSaveRequestDto;
import com.hyunsoo.springbootaws.dto.PostsUpdateRequestDto;
import com.hyunsoo.springbootaws.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }


    @ExceptionHandler(Exception.class)
    public Map<String,String> postAPiExceptionHandler(Exception exception){
        return Collections.singletonMap("message", exception.getMessage());
    }

}
