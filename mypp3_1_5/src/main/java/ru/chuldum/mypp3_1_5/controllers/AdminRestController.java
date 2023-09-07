package ru.chuldum.mypp3_1_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chuldum.mypp3_1_5.entities.Role;
import ru.chuldum.mypp3_1_5.entities.User;
import ru.chuldum.mypp3_1_5.entities.UserRoleWrap;
import ru.chuldum.mypp3_1_5.services.RoleServiceIntr;
import ru.chuldum.mypp3_1_5.services.UserServiceIntr;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private UserServiceIntr userService;

    @Autowired
    private RoleServiceIntr roleService;

    @PostMapping("/saveWrapper")
    public ResponseEntity<String> saveWrapper(UserRoleWrap userRoleWrap) {
        try {
            userService.saveWrapper(userRoleWrap);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при сохранении пользователя!");
        }
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @PostMapping("/deleteThroughId")
    public ResponseEntity<String> deleteThroughId(UserRoleWrap userRoleWrap) {
        try {
            userService.deleteViaId(userRoleWrap.getUser().getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при удалении пользователя!");
        }
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @GetMapping("/allUsers")
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/allRoles")
    public List<Role> allRoles() {
        return roleService.getAllRoles();
    }

}
