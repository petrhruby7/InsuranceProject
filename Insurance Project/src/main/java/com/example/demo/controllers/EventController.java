package com.example.demo.controllers;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.repositories.InsuranceRepository;
import com.example.demo.models.dto.EventDTO;
import com.example.demo.models.dto.InsuranceDTO;
import com.example.demo.models.dto.mappers.EventMapper;
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

import java.time.format.DateTimeFormatter;
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

    @Autowired
    private InsuranceServiceImpl insuranceService;

    //render all insurance events belonging to the logged-in user
    @GetMapping("events")
    public String renderEvents(Model model) {
        List<EventEntity> events = eventService.getEventsForCurrentUser();
        model.addAttribute("events", events);
        return "event/myEvents-Page";
    }

    //render form for create an insurance event
    @GetMapping("{insuranceId}/event/create")
    public String renderCreateEvent(@PathVariable Long insuranceId, @ModelAttribute EventDTO eventDTO, Model model) {
        InsuranceDTO insuranceDTO = insuranceService.getById(insuranceId);
        eventDTO.setInsuranceId(insuranceId);
        model.addAttribute("insuranceDTO", insuranceDTO);

        //reformats data into the pattern dd.MM.yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String startDateFormatter = insuranceDTO.getStartDate().format(formatter);
        String endDateFormatter = insuranceDTO.getEndDate().format(formatter);
        model.addAttribute("formatterStartDate", startDateFormatter);
        model.addAttribute("formatterEndDate", endDateFormatter);


        return "event/createEvent-Page";
    }

    //allow to create an insurance event
    @PostMapping("{insuranceId}/event/create")
    public String createEvent(@Valid @ModelAttribute EventDTO eventDTO,
                              BindingResult result,
                              @PathVariable Long insuranceId,
                              RedirectAttributes redirectAttributes,
                              Model model) {

        if (result.hasErrors()) {
            InsuranceDTO insuranceDTO = insuranceService.getById(insuranceId);
            model.addAttribute("insuranceDTO", insuranceDTO);
            return "event/createEvent-Page";
        }

        InsuranceEntity insuranceEntity = insuranceRepository.findByInsuranceId(insuranceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid insurance ID: " + insuranceId));

        try {
            eventService.createEvent(eventDTO, insuranceEntity);
        } catch (EventDateOutOfRangeException e) {
            result.rejectValue("eventDate", "error", "The event date must be between the insurance start and end dates.");
            InsuranceDTO insuranceDTO = insuranceService.getById(insuranceId);
            model.addAttribute("insuranceDTO", insuranceDTO);
            return "event/createEvent-Page";//checking that the date of the event is within the insurance period
        }

        redirectAttributes.addFlashAttribute("success", "Your insurance event is created");
        return "redirect:/insurance/events";
    }

    //render detail of insurance event
    @GetMapping("events/{eventId}")
    public String renderEventDetail(@PathVariable Long eventId,
                                    Model model) {
        EventDTO fetchedEvent = eventService.getById(eventId);
        InsuranceDTO insuranceDTO = insuranceService.getById(fetchedEvent.getInsuranceId());
        model.addAttribute("event", fetchedEvent);
        model.addAttribute("insuranceDTO", insuranceDTO);

        //reformats data into the pattern dd.MM.yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        String eventDateFormatter = fetchedEvent.getEventDate().format(formatter);
        model.addAttribute("formatterEventDate", eventDateFormatter);

        return "event/eventDetail-Page";
    }

    //render form for editing of insurance event
    @GetMapping("events/{eventId}/edit")
    public String renderEventEditForm(@PathVariable Long eventId,
                                      EventDTO eventDTO,
                                      Model model) {
        EventDTO fetchedEvent = eventService.getById(eventId);
        eventMapper.updateEventDTO(fetchedEvent, eventDTO);

        InsuranceDTO fetchedInsurance = insuranceService.getById(fetchedEvent.getInsuranceId());

        //reformats data into the pattern dd.MM.yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String startDateFormatter = fetchedInsurance.getStartDate().format(formatter);
        String endDateFormatter = fetchedInsurance.getEndDate().format(formatter);
        model.addAttribute("formattedStartDate", startDateFormatter);
        model.addAttribute("formattedEndDate", endDateFormatter);

        InsuranceDTO insuranceDTO = insuranceService.getById(fetchedEvent.getInsuranceId());
        model.addAttribute("eventDTO", eventDTO);
        model.addAttribute("insuranceDTO", insuranceDTO);
        return "event/updateEvent-Page";
    }

    // allow to edit existing insurance event
    @PostMapping("events/{eventId}/edit")
    public String editEvent(@PathVariable Long eventId,
                            @Valid @ModelAttribute EventDTO eventDTO,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (result.hasErrors()) {
            InsuranceDTO insuranceDTO = insuranceService.getById(eventDTO.getInsuranceId());
            model.addAttribute("eventDTO", eventDTO);
            model.addAttribute("insuranceDTO", insuranceDTO);
            return "event/updateEvent-Page";
        }
        InsuranceEntity insuranceEntity = insuranceRepository.findByInsuranceId(eventDTO.getInsuranceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid insurance ID: " + eventDTO.getInsuranceId()));

        try {
            eventDTO.setEventId(eventId);
            eventService.editEvent(eventDTO, insuranceEntity);
        } catch (EventDateOutOfRangeException e) {
            result.rejectValue("eventDate", "error", "The event date must be between the insurance start and end dates.");
            InsuranceDTO insuranceDTO = insuranceService.getById(eventDTO.getInsuranceId());
            model.addAttribute("eventDTO", eventDTO);
            model.addAttribute("insuranceDTO", insuranceDTO);
            return "event/updateEvent-Page";//checking that the date of the event is within the insurance period
        }

        redirectAttributes.addFlashAttribute("success", "Your insurance event is edited");
        return "redirect:/insurance/events";
    }

    // delete existing insurance events
    @GetMapping("events/{eventId}/delete")
    public String deleteEvent(@PathVariable Long eventId,
                              RedirectAttributes redirectAttributes) {
        eventService.removeEvent(eventId);

        redirectAttributes.addFlashAttribute("success", "Event was deleted");
        return "redirect:/insurance/events";
    }
}


