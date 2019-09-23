package org.encinas.dao.repository;

import org.encinas.dao.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<PatientEntity, Integer> {
}
