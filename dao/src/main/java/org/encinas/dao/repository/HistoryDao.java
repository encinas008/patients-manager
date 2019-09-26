package org.encinas.dao.repository;

import org.encinas.dao.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDao extends JpaRepository<History, Integer> {
}
