package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    @GetMapping("/")
    public String renderInsurances(){
        //todo přidat seznam pojištění

        return "/insurance/myInsurance-Page";
    }
}


//todo /insurance/create - k vytvoření insurance
//todo /insurance/ - zobrazení všech pojištění
//todo /insurance/detail - detail pojištění
//todo /insurance/update - uprava pojištění