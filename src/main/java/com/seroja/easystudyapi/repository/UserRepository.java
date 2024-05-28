package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phone);


    @Query(value = "SELECT u.* FROM app_user u JOIN user_roles ur ON u.username = ur.username WHERE ur.role = 'STUDENT'", nativeQuery = true)
    List<AppUser> findAllStudents();
}
