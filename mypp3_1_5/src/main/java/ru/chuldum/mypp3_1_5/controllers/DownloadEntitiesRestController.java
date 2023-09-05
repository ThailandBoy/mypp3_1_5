package ru.chuldum.mypp3_1_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chuldum.mypp3_1_5.entities.Role;
import ru.chuldum.mypp3_1_5.entities.User;
import ru.chuldum.mypp3_1_5.services.RoleServiceIntr;
import ru.chuldum.mypp3_1_5.services.UserServiceIntr;

import java.util.List;

@RestController
@RequestMapping("/users")
public class DownloadEntitiesRestController {

    @Autowired
    private UserServiceIntr userService;
    @Autowired
    private RoleServiceIntr roleService;

    @GetMapping("/allUsers")
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/allRoles")
    public List<Role> allRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/authUser")
    public User getAuthUser() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(authUser.getName());
    }

}
