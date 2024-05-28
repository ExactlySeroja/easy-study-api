package com.seroja.easystudyapi.repository;

import com.seroja.easystudyapi.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    List<Certificate> findCertificateByApplicationStudentId(int studentId);

}
