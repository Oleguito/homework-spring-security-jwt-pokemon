package com.pokemonreview.api.presentation.jwt.signup;

import com.pokemonreview.api.application.service.jwt.JwtService;
import com.pokemonreview.api.application.service.registration.SignUpService;
import com.pokemonreview.api.domain.model.user.UserEntity;
import com.pokemonreview.api.presentation.jwt.jwtresponse.JwtResponse;
import com.pokemonreview.api.presentation.jwt.signup.dto.SignUpQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/signup")
public class SignupController {
    
    private final JwtService jwtService;
    private final SignUpService signUpService;
    
    @PostMapping
    public ResponseEntity<JwtResponse> signup(
            @RequestBody SignUpQuery creds
    ) {
        UserEntity user = signUpService.signUp(creds.login(), creds.password());
        return ResponseEntity.ok(user.getTokens());
    }
    
}