package com.pokemonreview.api.application.service.registration;

import com.pokemonreview.api.application.service.jwt.JwtService;
import com.pokemonreview.api.domain.model.user.UserEntity;
import com.pokemonreview.api.infrastructure.repository.RoleRepository;
import com.pokemonreview.api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignUpService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    public UserEntity signUp(String login, String password) {
        final var found = userRepository.findByLogin(login);
        if(!found.isEmpty()) throw new IllegalArgumentException("Such user already exists");
        
        
        final var user = UserEntity.builder()
                .login(login)
                .password(passwordEncoder.encode(password))
                .roles(List.of(roleRepository.findByTitle("USER")))
                .build();
        
        final String accessToken = jwtService.generateAccessToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        
        user.setAccessToken(accessToken);
        user.setRefreshToken(refreshToken);
        
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        ));
        
        return userRepository.save(user);
    }
}
