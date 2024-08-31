package com.example.demo.controllers;

import com.example.demo.models.dto.InsuranceDTO;
import jakarta.validation.Valid;
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
    public String createInsurance(@Valid @ModelAttribute InsuranceDTO insurance,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderCreateInsurance(insurance);

        //zde budepráce s databazi

        System.out.println(insurance.getInsuranceType() + "-" + insurance.getAmount() + "-" + insurance.getInsuranceItem() + "-" + insurance.getStartDate() + "-" + insurance.getEndDate());
        return "redirect:/insurance";
    }
}


//todo /insurance/create - k vytvoření insurance
//todo /insurance/ - zobrazení všech pojištění
//todo /insurance/detail - detail pojištění
//todo /insurance/update - uprava pojištění
