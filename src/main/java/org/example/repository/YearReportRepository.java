package org.example.repository;

import org.example.model.YearReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface YearReportRepository extends JpaRepository<YearReport, UUID> {
}
