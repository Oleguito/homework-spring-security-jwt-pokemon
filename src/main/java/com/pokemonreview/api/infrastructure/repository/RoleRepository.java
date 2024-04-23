package com.pokemonreview.api.infrastructure.repository;

import com.pokemonreview.api.domain.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role findByTitle(String title);
}
