package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course> {

    default List<Course> findByAppUserId(Specification<Course> specification, Sort sort, Pageable pageable) {
        return findAll(Specification.where(specification), pageable).getContent();
    }
}
