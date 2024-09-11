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

import java.util.List;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceServiceImpl insuranceService;

    @Autowired
    private UserRepository userRepository; //nutné pro najití UserId

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private EventServiceImpl eventService;

    //zobrazení všech pojištění
    @GetMapping()
    public String renderInsurances(Model model){
        List<InsuranceEntity> insurances = insuranceService.getInsurancesForCurrentUser();
        model.addAttribute("insurances", insurances);
        return "/insurance/myInsurances-Page"; //vrací šablonu kde je seznam mých pojištění
    }
    //zobrazí formulář pro vytvoření pojištění
    @GetMapping("create")
    public String renderCreateInsurance(@ModelAttribute InsuranceDTO insurance){
        return "/insurance/createInsurance-Page";//vrací šablonu s formulářem pro tvorbu pojištění

    }

    //možnost vytvořit pojištění
    @PostMapping("create")
    public String createInsurance(@Valid @ModelAttribute InsuranceDTO insuranceDTO,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors())
            return renderCreateInsurance(insuranceDTO);

        // Najde aktuálního/přihlášeného uživatele
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUserName(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        try {
            insuranceService.createInsurance(insuranceDTO,user);
        } catch (InsuranceDurationException e) {
            result.rejectValue("startDate", "error","The insurance must be concluded for at least 6 months, maximum 5 years");
            result.rejectValue("endDate", "error","The insurance must be concluded for at least 6 months, maximum 5 years");
            return ("/insurance/createInsurance-Page"); //kontrola že je pojištění sjednáno alespon na 6 měsicu a nepřesahuje 5let
        } catch (InsuranceAmountException e) {
            result.rejectValue("amount", "error", "The sum insured must be greater than CZK 10,000 and less than CZK 10,000,000");
            return ("/insurance/createInsurance-Page");// kontrola že že pojistná částka je v hodnotě mezi 10 000 - 10 000 000
        }

        redirectAttributes.addFlashAttribute("success", "Insurance is created");
        return "redirect:/insurance"; //po vytvoření pojištění přesměruje zpět na seznam pojištění
    }

    //zobrazí detail pojištění
    @GetMapping("{insuranceId}")
    public String renderInsuranceDetail(
            @PathVariable Long insuranceId,
            Model model
    ){
        InsuranceDTO insuranceDTO = insuranceService.getById(insuranceId);
        model.addAttribute("insurance", insuranceDTO);

        List<EventDTO> events = eventService.getEventByInsuranceId(insuranceId);
        model.addAttribute("events", events);

        return "/insurance/insuranceDetail-Page";
    }
    @GetMapping("edit/{insuranceId}")
    public String renderEditInsuranceForm(
            @PathVariable Long insuranceId,
            InsuranceDTO insuranceDTO
    ){
        InsuranceDTO fetchedInsurance = insuranceService.getById(insuranceId);
        insuranceMapper.updateInsuranceDTO(fetchedInsurance,insuranceDTO);
        return "/insurance/updateInsurance-Page";
    }

    @PostMapping("edit/{insuranceId}")
    public String editArticle(
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

        }catch (InsuranceDurationException e) {
            result.rejectValue("startDate", "error","The insurance must be concluded for at least 6 months, maximum 5 years");
            result.rejectValue("endDate", "error","The insurance must be concluded for at least 6 months, maximum 5 years");
            return "/insurance/updateInsurance-Page"; //kontrola že je pojištění sjednáno alespon na 6 měsicu a nepřesahuje 5let
        } catch (InsuranceAmountException e) {
            result.rejectValue("amount", "error", "The sum insured must be greater than CZK 10,000 and less than CZK 10,000,000");
            return "/insurance/updateInsurance-Page";// kontrola že že pojistná částka je v hodnotě mezi 10 000 - 10 000 000
        }

        redirectAttributes.addFlashAttribute("success", "Insurance was updated");
        return "redirect:/insurance";
    }
    //todo: v tenhle moment funguje uprava špatně... pojištění které jsem založil 1.9 devaty a chci upravit 3.9 musím změnit na to že bylo založeno 3.9!!

    //smaže existující pojištění
    @GetMapping("delete/{insuranceId}")
    public String deleteInsurance (@PathVariable Long insuranceId, RedirectAttributes redirectAttributes) {

        try {
            insuranceService.removeInsurance(insuranceId);
        } catch (DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("error", "Cannot delete insurance with events attached. Please delete the associated events first");
            return "redirect:/insurance";
        }

        redirectAttributes.addFlashAttribute("success", "Insurance was deleted");
        return "redirect:/insurance";
    }
    //todo: předělat deleteInsurance z Get metody na Delete Metodu!

}




