package com.ex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Badge;
import com.ex.service.KinderService;

@RestController
public class BadgeController {
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="badges/{studentId}", method = RequestMethod.GET)
	public List<Badge> viewBadges(@PathVariable int studentId)
	{
		System.out.println("\n\n\n\nASDFASDFASDFASDF\n\n\n\n");
		return service.getBadgesByStudent(studentId);
	}
}
