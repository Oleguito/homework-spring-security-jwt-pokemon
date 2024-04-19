package com.pokemonreview.api.presentation.login;

import com.pokemonreview.api.infrastructure.repository.RoleRepository;
import com.pokemonreview.api.infrastructure.repository.UserRepository;
import com.pokemonreview.api.presentation.login.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        final var authentication
                = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getLogin(),
                                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity <>("User signed in successfully", HttpStatus.OK);
    }
    
}
