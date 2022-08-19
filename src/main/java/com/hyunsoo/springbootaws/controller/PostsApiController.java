package com.hyunsoo.springbootaws.controller;

import com.hyunsoo.springbootaws.dto.PostsResponseDto;
import com.hyunsoo.springbootaws.dto.PostsSaveRequestDto;
import com.hyunsoo.springbootaws.dto.PostsUpdateRequestDto;
import com.hyunsoo.springbootaws.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        //TODO validator 개발
        boolean isValidPosts =
                StringUtils.hasLength(requestDto.getTitle()) &&
                StringUtils.hasLength(requestDto.getAuthor()) &&
                StringUtils.hasLength(requestDto.getContent());
        if(!isValidPosts) {
            throw new IllegalArgumentException("올바른 게시글이 아닙니다.");
        }
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

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> postAPiExceptionHandler(Exception exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap("message", exception.getMessage()));
    }

}
