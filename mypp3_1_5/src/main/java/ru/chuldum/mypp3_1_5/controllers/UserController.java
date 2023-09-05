package ru.chuldum.mypp3_1_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chuldum.mypp3_1_5.entities.User;
import ru.chuldum.mypp3_1_5.entities.UserRoleWrap;
import ru.chuldum.mypp3_1_5.services.RoleServiceIntr;
import ru.chuldum.mypp3_1_5.services.UserServiceIntr;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RoleServiceIntr roleService;
    @Autowired
    private UserServiceIntr userService;

    @GetMapping("/")
    public String user(Model model) {

        Authentication userRole = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(userRole.getName());
        model.addAttribute("user", user);
        model.addAttribute("alluserlist", userService.getAllUsers());

        UserRoleWrap userRoleWrap = new UserRoleWrap();
        userRoleWrap.setUser(new User());
        userRoleWrap.setRoleList(roleService.getAllRoles());
        model.addAttribute("userRoleWrap", userRoleWrap);

        return "mainPage";
    }
}
