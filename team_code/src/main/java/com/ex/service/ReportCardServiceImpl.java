package com.ex.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.domain.ReportCard;
import com.ex.repo.ReportCardRepo;

@Service
@Transactional
public class ReportCardServiceImpl implements ReportCardService {
	
	@Autowired
	ReportCardRepo repo;

	@Override
	public ReportCard createReportCardEntry(ReportCard rc) {
		rc.setDate(new Timestamp(new Date().getTime()));
		return repo.save(rc);
	}

}
