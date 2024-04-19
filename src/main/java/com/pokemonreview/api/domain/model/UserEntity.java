package com.pokemonreview.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String login;
    
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(name = "users_roles",
    //         joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    //         inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    // )
    private List <Role> roles = new ArrayList<>();
}
