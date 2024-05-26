package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    @Query(value = "select * from certificate where student_id = ?", nativeQuery = true)
    List<Certificate> findCertificateByStudentId(int studentId);

}
