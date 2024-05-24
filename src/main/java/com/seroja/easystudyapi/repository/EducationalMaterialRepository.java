package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.EducationalMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalMaterialRepository extends JpaRepository<EducationalMaterial, Integer> {
}
