package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainControllers {

    @GetMapping("/")
    public String index(Model model) {
        return "public/index";
    }
    // главная страница!

}