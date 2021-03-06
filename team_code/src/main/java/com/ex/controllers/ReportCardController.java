package com.ex.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.ReportCard;
import com.ex.domain.Student;
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
		List<ReportCard> list = service.getAllReportCardsByTeacher(teacherId);
		Collections.sort(list);
		return list;
	}
	
	@RequestMapping(value="report-cards/{studentId}", method=RequestMethod.GET)
	public ReportCard getReportCardByStudent(@PathVariable int studentId) {
		return service.getReportCardByStudent(studentId);
	}
	
	@RequestMapping(value="report-cards/{studentId}", method=RequestMethod.PUT)
	public ReportCard updateReportCardByStudent(@PathVariable int studentId, @RequestBody ReportCard rc) {
		return service.updateReportCardByStudent(studentId, rc);
	}

}
