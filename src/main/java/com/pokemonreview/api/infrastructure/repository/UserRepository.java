package com.pokemonreview.api.infrastructure.repository;

import com.pokemonreview.api.domain.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional <UserEntity> findByLogin (String login);
    
    boolean existsByLogin(String login);
}
