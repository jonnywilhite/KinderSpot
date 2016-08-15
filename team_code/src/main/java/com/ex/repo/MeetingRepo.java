package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Meetings;
import com.ex.domain.User;

public interface MeetingRepo extends JpaRepository <Meetings, Integer> {

	
	List<Meetings> findByTeacher(User meeting);

	//Meetings updateBytApprove (Boolean approve);
	
	//List <Meetings> findByTeacher(Meetings meeting);
	
	//Meetings createMeeting (Meetings meeting);
	//Page<Meetings> findByTApproveTrueOrderByDateDesc (Pageable page); 

	

}
