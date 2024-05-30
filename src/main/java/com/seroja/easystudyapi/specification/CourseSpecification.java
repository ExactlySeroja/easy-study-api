package com.seroja.easystudyapi.specification;

import com.seroja.easystudyapi.entity.Application;
import com.seroja.easystudyapi.entity.Course;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseSpecification {

    public Specification<Course> hasName(String name) {
        return (root, query, criteriaBuilder) -> name == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.like(criteriaBuilder.lower(root.get("courseName")), "%" + name.toLowerCase() + "%");
    }

    public Specification<Course> hasCategoryId(Integer categoryId) {
        return (root, query, criteriaBuilder) -> categoryId == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public Specification<Course> hasTeacherId(Integer teacherId) {
        return (root, query, criteriaBuilder) -> teacherId == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("teacher").get("id"), teacherId);
    }

    public Specification<Course> hasMinPrice(Integer minPrice) {
        return (root, query, criteriaBuilder) -> minPrice == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.greaterThanOrEqualTo(root.get("coursePrice"), minPrice);
    }

    public Specification<Course> hasMaxPrice(Integer maxPrice) {
        return (root, query, criteriaBuilder) -> maxPrice == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.lessThanOrEqualTo(root.get("coursePrice"), maxPrice);
    }

    public Specification<Course> hasMinDate(LocalDate minDate) {
        return (root, query, criteriaBuilder) -> {
            if (minDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("courseStartDate"), minDate);
        };
    }

    public Specification<Course> hasMaxDate(LocalDate maxDate) {
        return (root, query, criteriaBuilder) -> {
            if (maxDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("courseEndDate"), maxDate);
        };
    }

    public Specification<Course> hasStudentId(Integer studentId) {
        return (root, query, criteriaBuilder) -> {
            if (studentId == null) {
                return criteriaBuilder.conjunction();
            }

            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<Application> applicationRoot = subquery.from(Application.class);
            subquery.select(applicationRoot.get("course").get("id"))
                    .where(criteriaBuilder.equal(applicationRoot.get("student").get("id"), studentId),
                            criteriaBuilder.equal(applicationRoot.get("course").get("id"), root.get("id")));

            return criteriaBuilder.exists(subquery);
        };
    }

    public Sort getSort(String priceSort, String startDateSort, String endDateSort) {
        List<Sort.Order> orders = new ArrayList<>();
        if (priceSort != null) {
            orders.add(new Sort.Order(priceSort.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "coursePrice"));
        }
        if (startDateSort != null) {
            orders.add(new Sort.Order(startDateSort.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "courseStartDate"));
        }
        if (endDateSort != null) {
            orders.add(new Sort.Order(endDateSort.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "courseEndDate"));
        }
        return Sort.by(orders);
    }
}
