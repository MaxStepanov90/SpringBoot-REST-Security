package com.stepanov.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserForm {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
}