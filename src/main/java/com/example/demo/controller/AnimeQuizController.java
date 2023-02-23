package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AnimeQuizController {


    @GetMapping("/anime-quiz")
    public String pageAnime(Model model) {

        return "public/anime-quiz";
    }

    @PostMapping("/accept_quiz_answer")
    public String acceptAnswer(){
        log.debug("REST request to accept quiz answer");
        return "public/anime-quiz";
    }
}
