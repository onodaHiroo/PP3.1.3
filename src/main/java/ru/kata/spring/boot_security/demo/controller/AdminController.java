package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.interfaces.RoleService;
import ru.kata.spring.boot_security.demo.service.interfaces.UserService;

import java.util.List;

@Controller()
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(@CurrentSecurityContext(expression = "authentication.principal") User principal, ModelMap model) {
        model.addAttribute("users", userService.getListOfUsers());
        model.addAttribute("user", principal);
        return "admin/index";
    }

    @GetMapping(value = "/addNewUserButton")
    public String addNewUser() {
        return "redirect:/admin/new";
    }

    @GetMapping(value = "/new")
    public String newPerson(@ModelAttribute("user") User user,
                            @CurrentSecurityContext(expression = "authentication.principal") User principal,
                            ModelMap modelMap) {
        modelMap.addAttribute("currentUser", principal);
        return "admin/new";
    }

    @PostMapping(value = "/new")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "listRoles") List<Long> roles) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.addNewUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/update")
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam(value = "listRoles") List<Long> roles) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam(value = "id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

}
