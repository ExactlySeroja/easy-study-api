package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Query(value = "select * from application where user_id = ?", nativeQuery = true)
    List<Application> findAllByStudentId(int id);

    @Query(value = "SELECT a.* FROM application a " +
            "JOIN course c ON a.course_id = c.course_id " +
            "JOIN app_user t ON c.teacher_id = t.id " +
            "WHERE t.id = :teacherId", nativeQuery = true)
    List<Application> findAllApplicationsByTeacherId(@Param("teacherId") int teacherId);

}
