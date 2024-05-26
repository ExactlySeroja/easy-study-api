package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {


    @Query(value = "select * from application where user_id = ?", nativeQuery = true)
    List<Application> findAllByStudentId(int id);

}
