package com.example.LESSON1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControllerA {
    @GetMapping("/SumOfTwoInts")
    public String sumofints() {
        int a = 1;
        int b = 2;
        int c = a + b;
        return Integer.toString(c);
    }

    @GetMapping("/SumOfTwoDoubles")
    public String sumofdoub() {
        double a = 1;
        double b = 2;
        double c = a + b;
        return Double.toString(c);
    }
}