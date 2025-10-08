package com.example.MyNotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class  TestController{

    @GetMapping("/api/public/hello")
    public String publicHello() {
        return "Hello from Public API!";
    }

    @GetMapping("/api/private/hello")
    public String privateHello() {
        return "Hello from Secured API!";
    }
}
