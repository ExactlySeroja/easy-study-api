package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.TaskPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskPerformanceRepository extends JpaRepository<TaskPerformance, Integer> {
    @Query("SELECT tp FROM TaskPerformance tp WHERE tp.edMaterial.id = :edMaterialId AND tp.doneBy.id = :doneById")
    Optional<TaskPerformance> findByEdMaterialIdAndDoneById(@Param("edMaterialId") Integer edMaterialId, @Param("doneById") Integer doneById);

    List<TaskPerformance> findByDoneById(Integer doneById);
}

