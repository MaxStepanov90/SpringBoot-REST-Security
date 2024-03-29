package com.stepanov.controllers;

import com.stepanov.forms.UserForm;
import com.stepanov.models.User;
import com.stepanov.repositories.UsersRepository;
import com.stepanov.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<User>getUsers(){
         return usersService.findAll();
    }

    @GetMapping("/users/{user-id}")
    public Optional<User> getUser(@PathVariable("user-id") Long userId) {
        return usersService.findById(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<Object>addUser(@RequestBody UserForm userForm){
        usersService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}
