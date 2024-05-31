package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.dto.query.EdMaterialAndTaskPerformanceProjection;
import com.seroja.easystudyapi.dto.query.EducationalMaterialWithTaskPerformanceDto;
import com.seroja.easystudyapi.entity.EducationalMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalMaterialRepository extends JpaRepository<EducationalMaterial, Integer> {

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

    @Query("SELECT new com.seroja.easystudyapi.dto.query.EducationalMaterialWithTaskPerformanceDto(" +
            "em.id, em.ed_material_name, em.content, em.dateOfUpload, " +
            "tp.id, tp.dateOfCompletion, tp.answer, tp.grade) " +
            "FROM EducationalMaterial em " +
            "LEFT JOIN TaskPerformance tp ON em.id = tp.edMaterial.id " +
            "WHERE em.theme.id = :themeId AND (tp.doneBy.id = :studentId OR tp.doneBy.id IS NULL)")
    List<EducationalMaterialWithTaskPerformanceDto> findAllMaterialsAndTaskPerformanceByThemeIdAndStudentId(@Param("themeId") Integer themeId, @Param("studentId") Integer studentId);

}
