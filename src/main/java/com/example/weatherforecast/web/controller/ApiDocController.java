package com.example.weatherforecast.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiDocController {

    @GetMapping("/doc")
    public String getApiDoc(){
        return "/static/html/docs/index.html";
    }
}
