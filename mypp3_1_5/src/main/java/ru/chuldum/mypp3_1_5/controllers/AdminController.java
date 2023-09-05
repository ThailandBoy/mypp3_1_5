package ru.chuldum.mypp3_1_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chuldum.mypp3_1_5.entities.User;
import ru.chuldum.mypp3_1_5.entities.UserRoleWrap;
import ru.chuldum.mypp3_1_5.services.RoleServiceIntr;
import ru.chuldum.mypp3_1_5.services.UserServiceIntr;




@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceIntr userService;

    @Autowired
    private RoleServiceIntr roleService;

    @GetMapping("/")
    public String admin(ModelMap model) {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findByUsername(authUser.getName());
        model.addAttribute("user", user);

        UserRoleWrap userRoleWrap = new UserRoleWrap();
        userRoleWrap.setUser(new User());
        userRoleWrap.setRoleList(roleService.getAllRoles());
        model.addAttribute("userRoleWrap", userRoleWrap);
        return "mainPage";
    }

    @PostMapping("/saveWrapper")
    public ResponseEntity<String> saveWrapper(@ModelAttribute("userRoleWrap") UserRoleWrap userRoleWrap) {
        try {
            userService.saveWrapper(userRoleWrap);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при сохранении пользователя!");
        }
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<String> deleteThroughId(@ModelAttribute("userRoleWrap") UserRoleWrap userRoleWrap) {
        try {
            userService.deleteViaId(userRoleWrap.getUser().getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при удалении пользователя!");
        }
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }
}
