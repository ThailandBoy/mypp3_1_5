package ru.chuldum.mypp3_1_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chuldum.mypp3_1_5.entities.User;
import ru.chuldum.mypp3_1_5.services.UserServiceIntr;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserServiceIntr userService;

    @GetMapping("/authUser")
    public User getAuthUser() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(authUser.getName());
    }
}
