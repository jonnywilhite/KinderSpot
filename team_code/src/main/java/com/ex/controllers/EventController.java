
package com.ex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.domain.Event;
import com.ex.domain.EventType;
import com.ex.service.KinderService;

@RestController
public class EventController {
	
	@Autowired
	private KinderService service;
	
	@RequestMapping(value = "event", method=RequestMethod.GET)
	public List<Event> getAllEvents(){
		List<Event> e = service.getAllEvents();
		/*System.out.println("List:");
		for (int i = 0; i < e.size(); i++){
			System.out.println( e.get(i).getName());
		}*/
		return service.getAllEvents();
	}
	

	@RequestMapping(value = "{studentId}/studentevent", method=RequestMethod.GET)
	public List<Event> getStudentEvents(@PathVariable int studentId){
		
		return service.getStudentEvents(studentId);
	}
	
	/*@RequestMapping (value = "event/{eventName}", method = RequestMethod.GET)
	public  Event getEventByEventName(@PathVariable String eventName){
		return service.getEventByEventName(eventName);
	}*/


	@RequestMapping (value = "event/Type", method = RequestMethod.GET)
	public List <EventType> getEventByType(){
		return service.getAllTypes();
	}
	
	@RequestMapping(value ="event/{eventName}", method = RequestMethod.POST)
	public Event createEvent (@RequestBody Event event, @PathVariable String eventName) {
		return service.createEvent(event, eventName);
	}

	@RequestMapping(value ="event/{eventName}", method =RequestMethod.PUT)
	public Event updateEvent (@RequestBody Event event, @PathVariable String eventName){
		return null;
	}
}