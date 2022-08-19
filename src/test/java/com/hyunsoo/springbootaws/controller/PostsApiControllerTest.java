package com.hyunsoo.springbootaws.controller;

import com.hyunsoo.springbootaws.domain.posts.Posts;
import com.hyunsoo.springbootaws.domain.posts.PostsRepository;
import com.hyunsoo.springbootaws.dto.PostsSaveRequestDto;
import com.hyunsoo.springbootaws.dto.PostsUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    void tearDown() throws Exception{
        log.info("==================");
        postsRepository.deleteAll();
        log.info("==================");
    }


    @Test
    @DisplayName("등록을 위한 테스트입니다.")
    void registerTest(){
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto
                .builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate
                .postForEntity(URI.create(url), requestDto, Long.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        Posts targetPost = all.get(0);
        assertThat(targetPost.getTitle()).isEqualTo(title);
        assertThat(targetPost.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("수정을 위한 테스트입니다.")
    void updateTest(){
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author").build());

        Long updatedId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto
                .builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

}