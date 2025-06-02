package com.example.demo3.event.controller;


import com.example.demo3.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;
@RequestMapping("/events")
    public List<String> getEvents() {

      return eventService.getEvents();
    }



}
