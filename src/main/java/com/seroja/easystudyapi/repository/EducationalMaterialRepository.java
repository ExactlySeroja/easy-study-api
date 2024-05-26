package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.EducationalMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalMaterialRepository extends JpaRepository<EducationalMaterial, Integer> {
    @Query(value = "select * from educational_material join public.theme t on t.theme_id = educational_material.theme_id where t.theme_id= ?", nativeQuery = true)
    List<EducationalMaterial> findEducationalMaterialByThemeId(Integer id);

}
