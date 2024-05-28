package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findCoursesByCourseNameLike(String name);

    List<Course> findCoursesByCategoryCategoryId(int categoryId);

    @Query(value = "SELECT c.course_id,  c.course_name, c.course_start_date, c.course_end_date, c.course_price, c.category_id, c.teacher_id FROM course c left join application a on c.course_id = a.course_id where a.user_id = ?", nativeQuery = true)
    List<Course> findByUserId(int id);

}
