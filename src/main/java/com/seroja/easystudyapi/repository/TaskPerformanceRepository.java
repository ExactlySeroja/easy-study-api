package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.TaskPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskPerformanceRepository extends JpaRepository<TaskPerformance, Integer> {

    @Query(value = "select * from task_performance join public.educational_material em on em.ed_material_id = task_performance.ed_material_id where em.ed_material_id = ?", nativeQuery = true)
    List<TaskPerformance> findByEdMaterialId(int EdMaterialId);

}
