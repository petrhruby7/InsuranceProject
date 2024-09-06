package com.example.demo.controllers;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.repositories.EventRepository;
import com.example.demo.data.repositories.InsuranceRepository;
import com.example.demo.models.dto.EventDTO;
import com.example.demo.models.dto.mappers.EventMapper;
import com.example.demo.models.dto.mappers.InsuranceMapper;
import com.example.demo.models.exceptions.EventDateOutOfRangeException;
import com.example.demo.models.services.EventServiceImpl;
import com.example.demo.models.services.InsuranceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/insurance")
public class EventController {
    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private EventMapper eventMapper;

    //zobrazeni všech mých pojištění
    @GetMapping("events")
    public String renderEvents(Model model) {
        List<EventEntity> events = eventService.getEventsForCurrentUser();
        model.addAttribute("events", events);
        return "event/myEvents-Page"; //vrací šablonu se seznamem mých pojištění
    }

    //zobrazeni formuláře pro vytvoření eventu
    @GetMapping("{insuranceId}/event/create")
    public String renderCreateEvent(@PathVariable Long insuranceId, @ModelAttribute EventDTO eventDTO) {
        eventDTO.setInsuranceId(insuranceId);
        return "event/createEvent-Page";
    }

    //možnost vytvořit event
    @PostMapping("{insuranceId}/event/create")
    public String createEvent(@Valid @ModelAttribute EventDTO eventDTO,
                              BindingResult result,
                              @PathVariable Long insuranceId,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return renderCreateEvent(eventDTO.getInsuranceId(), eventDTO);
        }

        InsuranceEntity insuranceEntity = insuranceRepository.findByInsuranceId(insuranceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid insurance ID: " + insuranceId));

        try {
            eventService.createEvent(eventDTO, insuranceEntity);
        } catch (EventDateOutOfRangeException e) {
            result.rejectValue("eventDate", "error", "The event date must be between the insurance start and end dates.");
            return "event/createEvent-Page";//kontrola že je datum udalosti v období kdy je uživatel pojištěný
        }

        redirectAttributes.addFlashAttribute("success", "Your insurance event is created");
        return "redirect:/insurance/events";
    }

    //zobrazí detail eventu
    @GetMapping("events/{eventId}")
    public String renderEventDetail(@PathVariable Long eventId,
                                    Model model) {
        EventDTO eventDTO = eventService.getById(eventId);
        model.addAttribute("event", eventDTO);
        return "event/eventDetail-Page";
    }

    //zobrazí update eventu
    @GetMapping("events/{eventId}/edit")
    public String renderEventEditForm(@PathVariable Long eventId,
                                      EventDTO eventDTO) {
        EventDTO fetchedEvent = eventService.getById(eventId);
        eventMapper.updateEventDTO(fetchedEvent, eventDTO);
        return "event/updateEvent-Page";
    }

    // umožní upravit event
    @PostMapping("events/{eventId}/edit")
    public String editEvent(@PathVariable Long eventId,
                            @Valid @ModelAttribute EventDTO eventDTO,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("eventDTO", eventDTO);
            return "event/updateEvent-Page";
        }
        InsuranceEntity insuranceEntity = insuranceRepository.findByInsuranceId(eventDTO.getInsuranceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid insurance ID: " + eventDTO.getInsuranceId()));

        try {
            eventDTO.setEventId(eventId);
            eventService.editEvent(eventDTO, insuranceEntity);
        } catch (EventDateOutOfRangeException e) {
            result.rejectValue("eventDate", "error", "The event date must be between the insurance start and end dates.");
            return "event/updateEvent-Page";//kontrola že je datum udalosti v období kdy je uživatel pojištěný
        }

        redirectAttributes.addFlashAttribute("success", "Your insurance event is edited");
        return "redirect:/insurance/events";
    }

    // smaže existující event
    @GetMapping("events/{eventId}/delete")
    public String deleteEvent(@PathVariable Long eventId,
                              RedirectAttributes redirectAttributes) {
        eventService.removeEvent(eventId);

        redirectAttributes.addFlashAttribute("success", "Event was deleted");
        return "redirect:/insurance/events";
    }
    //todo: předělat deleteInsurance z Get metody na Delete metodu!
}


