package ru.kata.spring.boot_security.demo.dao.interfaces;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Set<Role> getAllRoles();

    void addNewRole(Role role);

    Set<Role> findByIdRoles(List<Long> roles);
}
