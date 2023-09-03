package ru.chuldum.mypp3_1_5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginPage(){
        return "redirect:/login";
    }
}
