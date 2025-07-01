package com.example.springServer;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello from server!";
    }

    @GetMapping("/user")
    public String helloUser() {
        return "Hello user!";
    }
}