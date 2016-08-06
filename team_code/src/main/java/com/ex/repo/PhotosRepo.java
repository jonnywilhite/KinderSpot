package com.ex.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Photos;

public interface PhotosRepo extends JpaRepository <Photos,Integer> {
	
	//Photos uploadPhotos (Photos photo);
	
	//Photos deleteByPhoto (Photos photo);
	

}
