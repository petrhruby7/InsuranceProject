package com.example.demo.controllers;

import com.example.demo.data.entities.EventEntity;
import com.example.demo.data.entities.InsuranceEntity;
import com.example.demo.data.entities.UserEntity;
import com.example.demo.data.repositories.UserRepository;
import com.example.demo.models.dto.EventDTO;
import com.example.demo.models.dto.InsuranceDTO;
import com.example.demo.models.dto.mappers.InsuranceMapper;
import com.example.demo.models.exceptions.InsuranceAmountException;
import com.example.demo.models.exceptions.InsuranceDurationException;
import com.example.demo.models.services.EventServiceImpl;
import com.example.demo.models.services.InsuranceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceServiceImpl insuranceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private EventServiceImpl eventService;

    //render a list of insurances belonging to the logged-in user
    @GetMapping()
    public String renderInsurances(Model model) {
        List<InsuranceEntity> insurances = insuranceService.getInsurancesForCurrentUser();
        model.addAttribute("insurances", insurances);
        return "/insurance/myInsurances-Page";
    }

    //render form for creating insurance
    @GetMapping("create")
    public String renderCreateInsurance(@ModelAttribute InsuranceDTO insurance) {
        return "/insurance/createInsurance-Page";

    }

    //allow to create an insurance
    @PostMapping("create")
    public String createInsurance(@Valid @ModelAttribute InsuranceDTO insuranceDTO,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors())
            return renderCreateInsurance(insuranceDTO);

        // finds the currently logged user
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUserName(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        try {
            insuranceService.createInsurance(insuranceDTO, user);
        } catch (InsuranceDurationException e) {
            result.rejectValue("startDate", "error", "The insurance must be concluded for at least 6 months, maximum 5 years");
            result.rejectValue("endDate", "error", "The insurance must be concluded for at least 6 months, maximum 5 years");
            return ("/insurance/createInsurance-Page"); //checking that the insurance is arranged for at least 6 months and does not exceed 5 years
        } catch (InsuranceAmountException e) {
            result.rejectValue("amount", "error", "The sum insured must be greater than CZK 10,000 and less than CZK 10,000,000");
            return ("/insurance/createInsurance-Page");//check that the sum insured is between CZK 10,000 - CZK 10,000,000
        }

        redirectAttributes.addFlashAttribute("success", "Insurance is created");
        return "redirect:/insurance";
    }

    //render detail of insurance
    @GetMapping("{insuranceId}")
    public String renderInsuranceDetail(
            @PathVariable Long insuranceId,
            Model model
    ) {
        InsuranceDTO insuranceDTO = insuranceService.getById(insuranceId);
        model.addAttribute("insurance", insuranceDTO);

        List<EventDTO> events = eventService.getEventByInsuranceId(insuranceId);
        model.addAttribute("events", events);

        //reformats data into the pattern dd.MM.yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String startDateFormatter = insuranceDTO.getStartDate().format(formatter);
        String endDateFormatter = insuranceDTO.getEndDate().format(formatter);
        model.addAttribute("formatterStartDate", startDateFormatter);
        model.addAttribute("formatterEndDate", endDateFormatter);

        return "/insurance/insuranceDetail-Page";
    }

    //render form for editing insurance
    @GetMapping("edit/{insuranceId}")
    public String renderEditInsuranceForm(
            @PathVariable Long insuranceId,
            InsuranceDTO insuranceDTO, Model model
    ) {

        InsuranceDTO fetchedInsurance = insuranceService.getById(insuranceId);
        insuranceMapper.updateInsuranceDTO(fetchedInsurance, insuranceDTO);

        //reformats data into the pattern dd.MM.yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = fetchedInsurance.getStartDate().format(formatter);
        model.addAttribute("formattedStartDate", formattedDate);

        return "/insurance/updateInsurance-Page";
    }

    //allow to edit the data of insurance
    @PostMapping("edit/{insuranceId}")
    public String editInsurance(
            @PathVariable Long insuranceId,
            @Valid @ModelAttribute InsuranceDTO insuranceDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("insuranceDTO", insuranceDTO);
            return "/insurance/updateInsurance-Page";
        }

        try {
            insuranceDTO.setInsuranceId(insuranceId);
            insuranceService.editInsurance(insuranceDTO);

        } catch (InsuranceDurationException e) {
            result.rejectValue("startDate", "error", "The insurance must be concluded for at least 6 months, maximum 5 years");
            result.rejectValue("endDate", "error", "The insurance must be concluded for at least 6 months, maximum 5 years");
            return "/insurance/updateInsurance-Page"; //checking that the insurance is arranged for at least 6 months and does not exceed 5 years
        } catch (InsuranceAmountException e) {
            result.rejectValue("amount", "error", "The sum insured must be greater than CZK 10,000 and less than CZK 10,000,000");
            return "/insurance/updateInsurance-Page"; //check that the sum insured is between CZK 10,000 - CZK 10,000,000
        }

        redirectAttributes.addFlashAttribute("success", "Insurance was updated");
        return "redirect:/insurance";
    }


    //delete existing insurance
    @GetMapping("delete/{insuranceId}")
    public String deleteInsurance(@PathVariable Long insuranceId, RedirectAttributes redirectAttributes) {

        try {
            insuranceService.removeInsurance(insuranceId);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete insurance with events attached. Please delete the associated events first");
            return "redirect:/insurance";
        }

        redirectAttributes.addFlashAttribute("success", "Insurance was deleted");
        return "redirect:/insurance";
    }


}




