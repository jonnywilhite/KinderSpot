
package com.ex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Event;
import com.ex.service.KinderService;

@RestController
public class EventController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value = "event", method=RequestMethod.GET)
	public Page<Event> getEventPage(Integer page, Integer size){
		return service.getEventpage(page, size);
	}
	
	@RequestMapping (value = "event/{eventName}", method = RequestMethod.GET)
	public  Event getEventByEventName(String name){
		return service.getEventByEventName(name);
	}
	
	@RequestMapping(value ="event", method = RequestMethod.POST)
	public Event createEvent (@RequestBody Event event) {
		return service.createEvent(event);
	}

	@RequestMapping(value ="event/{eventName}", method =RequestMethod.PUT)
	public Event updateEvent (@RequestBody Event event, @PathVariable String eventName){
		return null;
	}
}