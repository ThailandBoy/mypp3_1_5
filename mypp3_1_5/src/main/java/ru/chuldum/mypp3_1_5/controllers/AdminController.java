package ru.chuldum.mypp3_1_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.chuldum.mypp3_1_5.entities.User;
import ru.chuldum.mypp3_1_5.entities.UserRoleWrap;
import ru.chuldum.mypp3_1_5.services.RoleService;
import ru.chuldum.mypp3_1_5.services.UserService;




@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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
    public ResponseEntity saveWrapper(@ModelAttribute("userRoleWrap") UserRoleWrap userRoleWrap, Model model) {
        try {
            userService.saveWrapper(userRoleWrap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity deleteThroughId(@ModelAttribute("userRoleWrap") UserRoleWrap userRoleWrap, Model model) {
        try {
            userService.deleteViaId(userRoleWrap.getUser().getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
