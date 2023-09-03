package ru.chuldum.mypp3_1_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chuldum.mypp3_1_5.entities.Role;
import ru.chuldum.mypp3_1_5.entities.User;
import ru.chuldum.mypp3_1_5.services.RoleService;
import ru.chuldum.mypp3_1_5.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class DownloadEntitiesRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/allUsers")
    public List<User> allUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/allRoles")
    public List<Role> allRoles(){
        return roleService.getAllRoles();
    }

}
