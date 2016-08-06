package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.ReportCard;
import com.ex.domain.User;

public interface ReportCardRepo extends JpaRepository<ReportCard, Integer> {

	List<ReportCard> findByTeacher(User teacher);
	
}
