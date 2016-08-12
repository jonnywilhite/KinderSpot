package com.ex.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Email;
import com.ex.domain.Student;
import com.ex.domain.User;
import com.ex.service.KinderService;

@RestController
public class EmailController 
{
	@Autowired
 	private KinderService service;
	
	//Set up to work with "http://localhost:8085/KinderSpot". The htmls are all placed in "webapp/resources"
	
	
	@RequestMapping(value="/{userId}/email", method = RequestMethod.POST)
	public void sendEmailToTeacher(@PathVariable String userId, @RequestBody Email email) throws IOException
	{
		int id = Integer.parseInt(userId);
		List<Student> students = service.getAllStudents();
		int teacherId = 0;
		for (int i = 0; i<students.size(); i++){
			if (students.get(i).getParent().getId() == id){
				teacherId = students.get(i).getTeacher().getId();
			}
		}
		System.out.println("Teacher Id: " + teacherId);
		service.sendEmail(id, teacherId, email.getSubject(), email.getBody());
	}
	
	@RequestMapping(value="/{userId}/emailParent", method = RequestMethod.POST)
	public void sendEmailToParent(@PathVariable String userId, @RequestBody Email email) throws IOException
	{
		int recipientId = 0;
		int id = Integer.parseInt(userId);
		if (!email.getRecipient().equals( "All")){
			recipientId = Integer.parseInt(email.getRecipient());
			
			service.sendEmail(id, recipientId+1, email.getSubject(), email.getBody());
		}
		else{
			System.out.println("SHOW ME WHAT YOU GOT");
			service.emailAllParents(id, email.getSubject(), email.getBody());
		}
		
		
	}
	
}