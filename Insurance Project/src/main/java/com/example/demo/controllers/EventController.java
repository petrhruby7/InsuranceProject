package com.example.demo.controllers;

import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.repositories.EventRepository;
import com.example.demo.data.repositories.InsuranceRepository;
import com.example.demo.models.dto.EventDTO;
import com.example.demo.models.dto.mappers.EventMapper;
import com.example.demo.models.dto.mappers.InsuranceMapper;
import com.example.demo.models.services.EventServiceImpl;
import com.example.demo.models.services.InsuranceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/insurance")
public class EventController {
    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private InsuranceServiceImpl insuranceService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private InsuranceMapper insuranceMapper;

    //todo zobrazeni všech pojištění

    //zobrazeni formuláře pro vytvoření eventu
    @GetMapping("{insuranceId}/event/create")
    public String renderCreateEvent(@PathVariable Long insuranceId, @ModelAttribute EventDTO eventDTO) {
        eventDTO.setInsuranceId(insuranceId);
        return "/event/createEvent-Page";
    }

    //možnost vytvořit event
    @PostMapping("{insuranceId}/event/create")
    public String createEvent(@Valid @ModelAttribute EventDTO eventDTO,
                              @PathVariable Long insuranceId,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return renderCreateEvent(insuranceId, eventDTO);

        InsuranceEntity insuranceEntity = insuranceRepository.findByInsuranceId(insuranceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid insurance ID: " + insuranceId));

        eventService.createEvent(eventDTO, insuranceEntity);

        redirectAttributes.addFlashAttribute("succes", "Your insurance event is created");
        return "redirect:/insurance"; //todo: přesměrovat na seznam eventu?
    }
    //todo zobrazí detail eventu

    //todo zobrazí update eventu

    //todo umožní upravit event

    //todo smaže existující event
}


