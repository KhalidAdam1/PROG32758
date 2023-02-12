package com.example.Assignment_2.Beans;


import lombok.Data;
import org.springframework.stereotype.Controller;
@Data
@Controller
public class Users {
    private String firstName;
    private String lastName;
    private String role;

    private final String[] roles ={"User", "Developers", "Tester"};
}
