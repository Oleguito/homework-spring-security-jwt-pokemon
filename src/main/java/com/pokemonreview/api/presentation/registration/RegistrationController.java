package com.pokemonreview.api.presentation.registration;

import com.pokemonreview.api.domain.model.UserEntity;
import com.pokemonreview.api.infrastructure.repository.RoleRepository;
import com.pokemonreview.api.infrastructure.repository.UserRepository;
import com.pokemonreview.api.presentation.registration.dto.RegisterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    public RegistrationController(AuthenticationManager authenticationManager,
                                  UserRepository userRepository,
                                  RoleRepository roleRepository,
                                  PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping
    public ResponseEntity <String> register(@RequestBody RegisterDto registerDto) {
        
        if(userRepository.existsByLogin(registerDto.getLogin())) {
            return new ResponseEntity <>("This login is taken",
                    HttpStatus.BAD_REQUEST);
        }
        
        final var userEntity = UserEntity.builder()
                .login(registerDto.getLogin())
                .password(
                        passwordEncoder.encode(
                                registerDto.getPassword())
                )
                .roles(List.of(roleRepository.findByTitle("USER")))
                .build();
        
        userRepository.save(userEntity);
        
        return ResponseEntity.ok("User Register Success!!!");
    }
}
