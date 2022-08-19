package com.hyunsoo.springbootaws.service;

import com.hyunsoo.springbootaws.domain.posts.Posts;
import com.hyunsoo.springbootaws.domain.posts.PostsRepository;
import com.hyunsoo.springbootaws.dto.PostsListResponseDto;
import com.hyunsoo.springbootaws.dto.PostsResponseDto;
import com.hyunsoo.springbootaws.dto.PostsSaveRequestDto;
import com.hyunsoo.springbootaws.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException(
                            "해당 게시글이 없습니다. id=" + id
                    );});
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("해당 게시글이 없습니다. id=" + id);
                });

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }



}
