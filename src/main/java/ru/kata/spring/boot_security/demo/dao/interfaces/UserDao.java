package ru.kata.spring.boot_security.demo.dao.interfaces;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    List<User> getListOfUsers();

    void removeUserById(long id);

    User show(Long id);

    User findByEmail(String email);

    void addOrUpdateUser(User user);
}
