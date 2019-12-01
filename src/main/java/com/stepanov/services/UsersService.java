package com.stepanov.services;

import com.stepanov.forms.UserForm;
import com.stepanov.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    void signUp(UserForm userForm);

    List<User> findAll();

    Optional<User> findById(Long userId);
}
