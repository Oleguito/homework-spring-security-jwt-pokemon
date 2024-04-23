package com.pokemonreview.api.application.service.authentication;

import com.pokemonreview.api.domain.model.user.UserEntity;
import com.pokemonreview.api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    
    public UserEntity authenticate(String login, String password) {
        final var user = findUserInRepo(login);
        if(
                passwordEncoder.matches(password, user.getPassword())
        )
            return user;
        throw new IllegalArgumentException("Passwords do not match");
    }
    
    public UserEntity findUserInRepo(String login) {
        return userRepository
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }
}
