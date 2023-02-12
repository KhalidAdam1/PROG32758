package com.example.Assignment_2.Controllers;

import com.example.Assignment_2.Beans.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private List<Users> usersList= new ArrayList<>();
    @GetMapping("/")
    public String showUser(Model model){
        model.addAttribute("usersList",usersList);
        model.addAttribute("user", new Users());
        return "index";
    }
    @PostMapping("/addUser")
    public String addUser(Model model, @ModelAttribute Users user){
        usersList.add(user);
        model.addAttribute("user", new Users());
        model.addAttribute("usersList",usersList);
        return "index";
    }
    @GetMapping("/clearTable")
    public String clearTable(Model model, @ModelAttribute Users user){
        usersList.clear();
        model.addAttribute("usersList",usersList);
        model.addAttribute("user", new Users());


        return "index";}

}
