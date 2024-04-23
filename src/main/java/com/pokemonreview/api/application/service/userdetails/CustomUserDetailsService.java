package com.pokemonreview.api.application.service.userdetails;

import com.pokemonreview.api.domain.model.user.UserEntity;
import com.pokemonreview.api.infrastructure.repository.RoleRepository;
import com.pokemonreview.api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        if(!userRepository.existsByLogin("admin")) {
            createAdminUser();
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity foundUser = findUser(username);
        
        return User.builder()
                .username(username)
                .password(foundUser.getPassword())
                .roles(String.valueOf(foundUser.getRoles()))
                .build();
    }
    
    private UserEntity findUser(String login) {
        return userRepository
                .findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("There is no such user, idiot!!!"));
    }
    
    private void createAdminUser() {
        userRepository.save(
            UserEntity.builder()
                    .login("admin")
                    .password(passwordEncoder.encode("12345"))
                    .roles(roleRepository.findAll())
                    .build()
        );
    }
}
