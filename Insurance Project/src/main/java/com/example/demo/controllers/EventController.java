package com.example.demo.controllers;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.models.dto.EventDTO;
import com.example.demo.models.dto.InsuranceDTO;
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

        eventDTO.setInsuranceId(insuranceId);
        prepareCreateFormData(eventDTO.getInsuranceId(), model);

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
            prepareCreateFormData(eventDTO.getInsuranceId(), model);
            return "event/createEvent-Page";
        }


        if (!eventService.isEventDateInRange(eventDTO.getEventDate(), insuranceId)) {
            result.rejectValue("eventDate", "error", "The event date must be between the insurance start and end dates.");
            prepareCreateFormData(eventDTO.getInsuranceId(), model);
            return "event/createEvent-Page";
        }
        eventService.createEvent(eventDTO, insuranceId);

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
        String eventDateFormatter = eventService.formatDate(fetchedEvent.getEventDate());
        model.addAttribute("formatterEventDate", eventDateFormatter);

        return "event/eventDetail-Page";
    }

    //render form for editing of insurance event
    @GetMapping("events/{eventId}/edit")
    public String renderEventEditForm(@PathVariable Long eventId,
                                      Model model) {
        //load event from database
        EventDTO fetchedEvent = eventService.getById(eventId);

        // Připrav data pro formulář
        prepareEditFormData(fetchedEvent.getInsuranceId(), fetchedEvent, model);
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
            // Připrav data pro formulář při chybě
            prepareEditFormData(eventDTO.getInsuranceId(), eventDTO, model);

            return "event/updateEvent-Page";
        }

        // Kontrola, zda je datum události v rozmezí platnosti pojištění
        if (!eventService.isEventDateInRange(eventDTO.getEventDate(), eventDTO.getInsuranceId())) {
            result.rejectValue("eventDate", "error", "The event date must be between the insurance start and end dates.");
            // Připrav data pro formulář při chybě

            prepareEditFormData(eventDTO.getInsuranceId(), eventDTO, model);
            return "event/updateEvent-Page";
        }

        eventService.editEvent(eventDTO, eventId);

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

    private void prepareCreateFormData(Long insuranceId, Model model) {
        InsuranceDTO insuranceDTO = insuranceService.getById(insuranceId);
        String startDateFormatter = eventService.formatDate(insuranceDTO.getStartDate());
        String endDateFormatter = eventService.formatDate(insuranceDTO.getEndDate());

        model.addAttribute("insuranceDTO", insuranceDTO);
        model.addAttribute("formatterStartDate", startDateFormatter);
        model.addAttribute("formatterEndDate", endDateFormatter);
    }

    private void prepareEditFormData(Long insuranceId, EventDTO eventDTO, Model model) {
        InsuranceDTO insuranceDTO = insuranceService.getById(insuranceId);
        String startDateFormatter = eventService.formatDate(insuranceDTO.getStartDate());
        String endDateFormatter = eventService.formatDate(insuranceDTO.getEndDate());
        String eventDateFormatter = eventService.formatDate(eventDTO.getEventDate());

        model.addAttribute("eventDTO", eventDTO);
        model.addAttribute("insuranceDTO", insuranceDTO);
        model.addAttribute("formattedStartDate", startDateFormatter);
        model.addAttribute("formattedEndDate", endDateFormatter);
        model.addAttribute("formattedEventDate", eventDateFormatter);
    }
}


