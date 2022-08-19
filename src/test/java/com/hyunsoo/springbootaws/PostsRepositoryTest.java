package com.hyunsoo.springbootaws;

import com.hyunsoo.springbootaws.domain.posts.Posts;
import com.hyunsoo.springbootaws.domain.posts.PostsRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시판 글을 가져오는 테스트입니다.")
    public void getPostsTest(){
        //given
        String title = "테스트게시글";
        String content = "테스트본문";
        postsRepository.save(Posts.builder()
                                  .title(title)
                                  .content(content)
                                  .author("blessdutch@naver.com")
                                  .build());
        //when
        List<Posts> postsList = postsRepository.findAll();


        //then
        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

}
