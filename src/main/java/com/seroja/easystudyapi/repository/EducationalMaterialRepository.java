package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.dto.query.EdMaterialAndTaskPerformanceProjection;
import com.seroja.easystudyapi.entity.EducationalMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalMaterialRepository extends JpaRepository<EducationalMaterial, Integer> {
    @Query(value = "select * from educational_material join public.theme t on t.theme_id = educational_material.theme_id where t.theme_id= ?", nativeQuery = true)
    List<EducationalMaterial> findEducationalMaterialByThemeId(Integer id);

    @Query(value = "SELECT " +
            "em.ed_material_id as id, " +
            "em.ed_material_name as name, " +
            "em.date_of_upload as dateOfUpload, " +
            "tp.task_id as taskPerformanceDtoId, " +
            "tp.ed_material_id as taskPerformanceDtoEdMaterialId, " +
            "tp.done_by as taskPerformanceDtoStudentId, " +
            "tp.date_of_completion as taskPerformanceDtoDateOfCompletion, " +
            "tp.answer as taskPerformanceDtoAnswer, " +
            "tp.grade as taskPerformanceDtoGrade " +
            "FROM educational_material em " +
            "LEFT JOIN task_performance tp ON em.ed_material_id = tp.ed_material_id " +
            "WHERE em.ed_material_id = :edMaterialId",
            nativeQuery = true)
    List<EdMaterialAndTaskPerformanceProjection> findEducationalMaterialAndTaskPerformanceById(@Param("edMaterialId") int edMaterialId);

}
