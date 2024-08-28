package ru.kata.spring.boot_security.demo.service.interfaces;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getListOfUsers();

    void removeUserById(long id);

    User show(Long id);

    void addOrUpdateUser(User user);

}
