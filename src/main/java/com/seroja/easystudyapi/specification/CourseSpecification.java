package com.seroja.easystudyapi.specification;

import com.seroja.easystudyapi.entity.Course;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CourseSpecification {

    public Specification<Course> hasName(String name) {
        return (root, query, criteriaBuilder) -> name == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.like(criteriaBuilder.lower(root.get("courseName")), "%" + name.toLowerCase() + "%");
    }

    public Specification<Course> hasCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            if (categoryName == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Object, Object> category = root.join("category", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(category.get("name")), "%" + categoryName.toLowerCase() + "%");
        };
    }

    public Specification<Course> orderByPrice(String priceOrder) {
        return (root, query, criteriaBuilder) -> {
            if (priceOrder == null) {
                return criteriaBuilder.conjunction();
            }
            if (priceOrder.equalsIgnoreCase("priceAsc")) {
                query.orderBy(criteriaBuilder.asc(root.get("coursePrice")));
            } else if (priceOrder.equalsIgnoreCase("priceDesc")) {
                query.orderBy(criteriaBuilder.desc(root.get("coursePrice")));
            }
            return criteriaBuilder.conjunction();
        };
    }

    public Specification<Course> orderByDate(String dateOrder) {
        return (root, query, criteriaBuilder) -> {
            if (dateOrder == null) {
                return criteriaBuilder.conjunction();
            }
            if (dateOrder.equalsIgnoreCase("dateAsc")) {
                query.orderBy(criteriaBuilder.asc(root.get("courseStartDate")));
            } else if (dateOrder.equalsIgnoreCase("dateDesc")) {
                query.orderBy(criteriaBuilder.desc(root.get("courseStartDate")));
            }
            return criteriaBuilder.conjunction();
        };
    }
}
