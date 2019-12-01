package com.stepanov.services;

import com.stepanov.forms.LoginForm;
import com.stepanov.transfer.TokenDto;

public interface LoginService {

    TokenDto login(LoginForm loginForm);
}
