package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.interfaces.RoleService;
import ru.kata.spring.boot_security.demo.service.interfaces.UserService;

import java.util.Collections;
import java.util.Set;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public ApplicationRunnerImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        roleService.addNewRole(new Role(1L, "ROLE_USER"));
        roleService.addNewRole(new Role(2L, "ROLE_ADMIN"));

        User admin = new User("admin", "admin", "admin");
        User user = new User("user", "user", "user");

        admin.setRoles(roleService.getAllRoles());
        userService.addNewUser(admin);

        user.setRoles(Set.copyOf(Collections.singletonList(roleService.getRoleById(1L))));
        userService.addNewUser(user);

    }
}
