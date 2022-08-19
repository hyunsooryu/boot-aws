package com.hyunsoo.springbootaws.controller;

import com.hyunsoo.springbootaws.dto.PostsResponseDto;
import com.hyunsoo.springbootaws.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(ModelMap modelMap){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            modelMap.addAttribute("userName",user.getName());
        }
        modelMap.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
