package com.ex.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Meetings;

public interface MeetingRepo extends JpaRepository <Meetings, Integer> {

	//Meetings updateBytApprove (Boolean approve);
	
	//Meetings createMeeting (Meetings meeting);
	//Page<Meetings> findByTApproveTrueOrderByDateDesc (Pageable page); 

	

}
