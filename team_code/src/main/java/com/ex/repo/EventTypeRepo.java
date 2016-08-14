package com.ex.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.domain.EventType;


public interface EventTypeRepo extends JpaRepository <EventType, Integer> {

}
