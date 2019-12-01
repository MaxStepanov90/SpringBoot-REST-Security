package com.stepanov.security.provider;

import com.stepanov.models.Token;
import com.stepanov.models.User;
import com.stepanov.repositories.TokensRepository;
import com.stepanov.security.token.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokensRepository tokensRepository;

    @Qualifier("inMemoryUserDetailsManager")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        Optional<Token> tokenCandidate = tokensRepository.findOneByValue(tokenAuthentication.getName());
        if (tokenCandidate.isPresent()){
            UserDetails userDetails =  userDetailsService.loadUserByUsername(tokenCandidate.get().getUser().getLogin());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        }
        else{
            throw new IllegalArgumentException("Bad token");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        TokenAuthentication.class.equals(aClass);
        return false;
    }
}
