package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findUserByUsername(String username);

    boolean existsByUsername(String username);

}
