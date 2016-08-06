package com.ex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.ReportCard;
import com.ex.service.KinderService;

@RestController
public class ReportCardController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="report-cards", method=RequestMethod.POST)
	public ReportCard createReportCardEntry(@RequestBody ReportCard rc) {
		return service.createReportCardEntry(rc);
	}
	
	@RequestMapping(value="{teacherId}/report-cards", method=RequestMethod.GET)
	public List<ReportCard> getAllReportCardsByTeacher(@PathVariable int teacherId) {
		return service.getAllReportCardsByTeacher(teacherId);
	}

}
