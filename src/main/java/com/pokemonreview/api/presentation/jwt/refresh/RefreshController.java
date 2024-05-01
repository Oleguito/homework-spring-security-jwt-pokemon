package com.pokemonreview.api.presentation.jwt.refresh;

import com.pokemonreview.api.application.service.authentication.AuthenticationService;
import com.pokemonreview.api.application.service.jwt.JwtService;
import com.pokemonreview.api.presentation.jwt.jwtresponse.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/refresh")
@RequiredArgsConstructor
public class RefreshController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    
    @PostMapping
    public ResponseEntity<JwtResponse> refresh(
            @RequestBody String refreshToken
    ) {
        System.out.println("");
        System.out.print("\"");
        System.out.println("refreshToken: " + refreshToken);
        System.out.print("\"");
        System.out.println("");
        
        final var username = jwtService.extractUsername(refreshToken);
        final var user = authenticationService.findUserInRepo(username);
        
        if(jwtService.isTokenValidAndNotExpired(refreshToken, user)) {
            user.setAccessToken(jwtService.generateAccessToken(user));
            user.setRefreshToken(jwtService.generateRefreshToken(user));
            return ResponseEntity.ok(user.getTokens());
        }
        
        throw new RuntimeException("Token expired");
    }

}
