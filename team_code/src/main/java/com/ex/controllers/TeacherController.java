package com.ex.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ex.domain.Photos;
import com.ex.service.KinderService;

@RestController
@MultipartConfig(fileSizeThreshold = 20_971_520)
public class TeacherController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="photos/{eventId}", method=RequestMethod.POST)
	public Photos uploadPhoto(@RequestBody MultipartFile key, @PathVariable int eventId) {
		
		//System.out.println("entering method...");
		
		File file = new File(key.getOriginalFilename());
		
		//System.out.println("key is: " + key.getOriginalFilename());
		
		try {
			//System.out.println("transfering..");
			key.transferTo(file);
			//System.out.println("transferred...");
			return service.uploadPhoto(file, eventId);
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@RequestMapping(value="photos", method=RequestMethod.GET)
	public List<Photos> getPhotos() {
		return service.getAllPhotos();
	}
	
	@RequestMapping(value="photos/{eventId}", method=RequestMethod.GET)
	public List<Photos> getPhotosByEvent(@PathVariable int eventId) {
		return service.getPhotosByEvent(eventId);
	}

}
