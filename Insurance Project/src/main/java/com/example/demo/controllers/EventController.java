package com.example.demo.controllers;

import com.example.demo.data.repositories.InsuranceRepository;
import com.example.demo.models.dto.EventDTO;
import com.example.demo.models.dto.InsuranceDTO;
import com.example.demo.models.dto.mappers.EventMapper;
import com.example.demo.models.services.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insurance/event")
public class EventController {
    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private EventMapper eventMapper;

    //todo zobrazeni všech pojištění

    //todo zobrazeni formuláře pro vytvoření eventu

    /**
     *
     * @param eventDTO
     * @return
     */
    @GetMapping("create")
    public String renderCreateEvent(@ModelAttribute EventDTO eventDTO){
        return "/event/createEvent-Page";
    }
    //todo možnost vytvořit event

    //todo zobrazí detail eventu

    //todo zobrazí update eventu

    //todo umožní upravit event

    //todo smaže existující event
}


