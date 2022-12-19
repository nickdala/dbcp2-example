package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {
    
    @GetMapping("/hello")
    Hello getHello() {
        log.info("In hello method");
        return new Hello("nick");
    }
}
