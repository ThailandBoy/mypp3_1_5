package ru.chuldum.mypp3_1_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chuldum.mypp3_1_5.entities.Role;
import ru.chuldum.mypp3_1_5.entities.User;
import ru.chuldum.mypp3_1_5.entities.UserDTO;
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

    @PutMapping("/saveWrapper")
    public ResponseEntity<String> saveWrapper(@RequestBody UserDTO userDTO) {
        try {
            userService.saveWrapper(userDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при сохранении пользователя!");
        }
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @DeleteMapping("/deleteThroughId")
    public ResponseEntity<String> deleteThroughId(@RequestBody UserDTO userDTO) {
        try {
            userService.deleteViaId(userDTO.getId());
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
