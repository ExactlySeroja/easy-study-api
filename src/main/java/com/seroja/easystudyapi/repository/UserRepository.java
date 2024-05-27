package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.dto.query.ProfileDto;
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


    @Query(value = "select u.id, u.full_name, u.username, u.date_of_birth, u.phone_number, u.email from app_user u  JOIN public.user_roles ur ON u.username = ur.username where role = 'STUDENT'", nativeQuery = true)
    List<ProfileDto> finAllStudents();
}
