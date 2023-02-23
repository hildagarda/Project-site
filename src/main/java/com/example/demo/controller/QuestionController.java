package com.example.demo.controller;

import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;



}
