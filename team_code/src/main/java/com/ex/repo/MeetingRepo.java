package com.ex.repo;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.Meetings;

public interface MeetingRepo extends JpaRepository <Meetings, Integer> {
	
	Meetings createMeeting (Meetings meeting);
	Meetings findByDate (Timestamp date);
	Meetings updateMeetingByTApprove (Meetings meeting);
	Page<Meetings> findByTApproveTrueOrderByDateDesc (Pageable page); 

}
