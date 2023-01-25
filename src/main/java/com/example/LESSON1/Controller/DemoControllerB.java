package com.example.LESSON1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControllerB {
    @GetMapping("/ConcatTwoStrings")
    public String twoStrings(){
        String a= "Hello world ";
        String b= "Welcome";
        String c = a+b;
        return c;
    }
}