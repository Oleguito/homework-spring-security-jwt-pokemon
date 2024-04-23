package com.pokemonreview.api.domain.model.user;

import com.pokemonreview.api.domain.model.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pokemonreview.api.presentation.jwt.jwtresponse.JwtResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
public class UserEntity implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String login;
    
    private String password;
    
    @Setter
    private String accessToken;
    
    @Setter
    private String refreshToken;
    
    @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(name = "users_roles",
    //         joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    //         inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    // )
    private List <Role> roles = new ArrayList<>();
    
    @Override
    public Collection <? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(
                role -> {
                    return new SimpleGrantedAuthority(role.getTitle());
                }
        ).collect(Collectors.toList());
    }
    
    public JwtResponse getTokens() {
        return new JwtResponse(accessToken, refreshToken);
    }
    
    @Override
    public String getUsername() {
        return login;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
