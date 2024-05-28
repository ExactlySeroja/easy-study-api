package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.TaskPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskPerformanceRepository extends JpaRepository<TaskPerformance, Integer> {
}
