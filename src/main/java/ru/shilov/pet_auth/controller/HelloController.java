package ru.shilov.pet_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/authenticated")
    public String helloAuthenticated() {
        return "Hello authenticated";
    }

    @GetMapping("/manager")
    public String helloManager() {
        return "Hello manager";
    }
}
