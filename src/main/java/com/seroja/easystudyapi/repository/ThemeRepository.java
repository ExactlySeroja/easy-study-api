package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    @Query(value = "select theme.theme_id, theme.theme_name, theme.course_id from theme join public.course c on c.course_id = theme.course_id where c.course_id = ?", nativeQuery = true)
    List<Theme> findByCourseId(int courseId);

}
