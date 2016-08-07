package com.ex.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ex.domain.Photos;
//import com.ex.domain.Photos;
import com.ex.service.KinderService;

@RestController
@MultipartConfig(fileSizeThreshold = 20971520)
public class TeacherController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value="photos", method=RequestMethod.POST)
	public Photos uploadPhoto(@RequestBody MultipartFile key) {
		Photos photo = new Photos();
		File file = new File(key.getOriginalFilename());
		
		try {
			
			key.transferTo(file);
			photo.setPhoto(file.getPath());
			photo.setEvent(null);
			return service.uploadPhoto(photo, file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}
