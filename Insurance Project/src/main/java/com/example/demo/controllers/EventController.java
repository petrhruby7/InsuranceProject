package com.example.demo.controllers;

import com.example.demo.data.repositories.InsuranceRepository;
import com.example.demo.models.dto.mappers.EventMapper;
import com.example.demo.models.services.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {
    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private EventMapper eventMapper;
}
