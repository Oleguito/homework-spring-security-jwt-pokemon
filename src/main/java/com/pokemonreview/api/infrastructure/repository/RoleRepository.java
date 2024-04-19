package com.pokemonreview.api.infrastructure.repository;

import com.pokemonreview.api.domain.model.Role;
import com.pokemonreview.api.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role findByTitle(String title);
}
