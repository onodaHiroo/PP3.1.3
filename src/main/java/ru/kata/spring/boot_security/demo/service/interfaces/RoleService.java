package ru.kata.spring.boot_security.demo.service.interfaces;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();

    void addNewRole(Role role);

    Set<Role> findByIdRoles(List<Long> roles);

}
