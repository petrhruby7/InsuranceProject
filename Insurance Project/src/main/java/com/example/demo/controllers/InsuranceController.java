package com.example.demo.controllers;

import com.example.demo.models.dto.InsuranceDTO;
import com.example.demo.models.services.InsuranceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceServiceImpl insuranceService;

    //zobrazení všech pojištění
    @GetMapping()
    public String renderInsurances(){
        //todo přidat seznam pojištění

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
                                  RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderCreateInsurance(insuranceDTO);

        //zde budepráce s databazi
        insuranceService.createInsurance(insuranceDTO);
        //System.out.println(insurance.getInsuranceType() + "-" + insurance.getAmount() + "-" + insurance.getInsuredItem() + "-" + insurance.getStartDate() + "-" + insurance.getEndDate());
        redirectAttributes.addFlashAttribute("success", "Insurance is created");

        return "redirect:/insurance";
    }
}


//todo /insurance/create - k vytvoření insurance
//todo /insurance/ - zobrazení všech pojištění
//todo /insurance/detail - detail pojištění
//todo /insurance/update - uprava pojištění
