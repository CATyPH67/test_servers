package com.example.springServer;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
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