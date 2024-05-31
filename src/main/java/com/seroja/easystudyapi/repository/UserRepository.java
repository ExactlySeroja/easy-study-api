package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.dto.query.StudentTaskPerformanceDto;
import com.seroja.easystudyapi.dto.query.TaskPerformanceDetailsDto;
import com.seroja.easystudyapi.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phone);

    @Query("SELECT new com.seroja.easystudyapi.dto.query.TaskPerformanceDetailsDto(tp.id, em.ed_material_name, tp.dateOfCompletion, tp.answer, tp.grade, u.id) " +
            "FROM TaskPerformance tp " +
            "JOIN tp.doneBy u " +
            "JOIN tp.edMaterial em " +
            "JOIN em.theme t " +
            "JOIN t.course c " +
            "WHERE c.id = :courseId")
    List<TaskPerformanceDetailsDto> findTaskPerformancesByCourseId(@Param("courseId") Integer courseId);

    @Query("SELECT new com.seroja.easystudyapi.dto.query.StudentTaskPerformanceDto(u.id, u.fullName) " +
            "FROM Application a " +
            "JOIN a.student u " +
            "JOIN a.course c " +
            "WHERE c.id = :courseId")
    List<StudentTaskPerformanceDto> findStudentsByCourseId(@Param("courseId") Integer courseId);

    @Query(value = "SELECT u.* FROM app_user u JOIN user_roles ur ON u.username = ur.username WHERE ur.role = 'STUDENT'", nativeQuery = true)
    List<AppUser> findAllStudents();
}
