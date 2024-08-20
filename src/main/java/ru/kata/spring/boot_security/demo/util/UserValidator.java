package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.interfaces.UserService;

import javax.persistence.NoResultException;

@Component
public class UserValidator implements Validator, MyValidator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userService.loadUserByUsername(user.getEmail());
        } catch (NoResultException | UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("email", "", "Email занят");
    }

    public boolean userInDataBase(User user){
        try {
            userService.loadUserByUsername(user.getEmail());
        } catch (NoResultException | UsernameNotFoundException exception){
            return false;
        }
        return true;
    }
}
