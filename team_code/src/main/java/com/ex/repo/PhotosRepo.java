package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Event;
import com.ex.domain.Photos;

public interface PhotosRepo extends JpaRepository <Photos,Integer> {
	
	List<Photos> findByEvent(Event event);
	
	//Photos uploadPhotos (Photos photo);
	
	//Photos deleteByPhoto (Photos photo);
	

}
