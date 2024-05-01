package com.pokemonreview.api.presentation.jwt.login;

import com.pokemonreview.api.application.service.authentication.AuthenticationService;
import com.pokemonreview.api.application.service.jwt.JwtService;
import com.pokemonreview.api.infrastructure.repository.RoleRepository;
import com.pokemonreview.api.infrastructure.repository.UserRepository;
import com.pokemonreview.api.presentation.jwt.jwtresponse.JwtResponse;
import com.pokemonreview.api.presentation.jwt.login.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@SessionAttributes
public class LoginController {
    // private final AuthenticationManager authenticationManager;
    // private final UserRepository userRepository;
    // private final RoleRepository roleRepository;
    // private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    
    @PostMapping
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto) {
        final var user = authenticationService.authenticate(
                loginDto.getLogin(),
                loginDto.getPassword());
        
        user.setAccessToken(jwtService.generateAccessToken(user));
        user.setRefreshToken(jwtService.generateRefreshToken(user));
        
        return ResponseEntity.ok(user.getTokens());
    }
    
}
