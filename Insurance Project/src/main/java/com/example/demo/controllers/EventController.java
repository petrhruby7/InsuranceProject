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

    /**
     * Handles creating a new insurance event.
     * Validates the input, ensuring that the event date falls within the insurance coverage period.
     *
     * @param eventDTO           DTO containing the event data.
     * @param result             Binding result for validation errors.
     * @param insuranceId        ID of the insurance to which the event is related.
     * @param redirectAttributes Attributes for passing flash messages to the view.
     * @param model              Model to pass data to the view.
     * @return A redirect to the list of events if successful, otherwise re-renders the form.
     */
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

        //Checks if the given event date is within the coverage period of the specified insurance.
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
        String eventDateFormatter = eventService.formatDate(fetchedEvent.getEventDate());

        model.addAttribute("event", fetchedEvent);
        model.addAttribute("insuranceDTO", insuranceDTO);
        model.addAttribute("formatterEventDate", eventDateFormatter);
        return "event/eventDetail-Page";
    }

    //render form for editing of insurance event
    @GetMapping("events/{eventId}/edit")
    public String renderEventEditForm(@PathVariable Long eventId,
                                      Model model) {
        EventDTO fetchedEvent = eventService.getById(eventId);
        prepareEditFormData(fetchedEvent.getInsuranceId(), fetchedEvent, model);
        return "event/updateEvent-Page";
    }

    /**
     * Handles editing an existing insurance event.
     * Validates that the event date is within the coverage period of the related insurance.
     *
     * @param eventId            ID of the event to be edited.
     * @param eventDTO           DTO containing updated event details.
     * @param result             Binding result for validation errors.
     * @param redirectAttributes Attributes for passing flash messages to the view.
     * @param model              Model to pass data to the view.
     * @return A redirect to the list of events if successful, otherwise re-renders the edit form.
     */
    @PostMapping("events/{eventId}/edit")
    public String editEvent(@PathVariable Long eventId,
                            @Valid @ModelAttribute EventDTO eventDTO,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {

        if (result.hasErrors()) {
            prepareEditFormData(eventDTO.getInsuranceId(), eventDTO, model);
            return "event/updateEvent-Page";
        }

        //Checks if the given event date is within the coverage period of the specified insurance.
        if (!eventService.isEventDateInRange(eventDTO.getEventDate(), eventDTO.getInsuranceId())) {
            result.rejectValue("eventDate", "error", "The event date must be between the insurance start and end dates.");
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

    /**
     * Prepares the necessary form data for creating an insurance event.
     *
     * @param insuranceId ID of the insurance for which the event is being created.
     * @param model       Model object to which form data attributes are added.
     */
    private void prepareCreateFormData(Long insuranceId, Model model) {
        InsuranceDTO insuranceDTO = insuranceService.getById(insuranceId);
        String startDateFormatter = eventService.formatDate(insuranceDTO.getStartDate());
        String endDateFormatter = eventService.formatDate(insuranceDTO.getEndDate());

        model.addAttribute("insuranceDTO", insuranceDTO);
        model.addAttribute("formatterStartDate", startDateFormatter);
        model.addAttribute("formatterEndDate", endDateFormatter);
    }

    /**
     * Prepares the necessary form data for editing an insurance event.
     *
     * @param insuranceId ID of the insurance associated with the event being edited.
     * @param eventDTO    Event data transfer object containing event details.
     * @param model       Model object to which form data attributes are added.
     */
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