package com.ex.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.ReportCard;

public interface ReportCardRepo extends JpaRepository<ReportCard, Integer> {

}
