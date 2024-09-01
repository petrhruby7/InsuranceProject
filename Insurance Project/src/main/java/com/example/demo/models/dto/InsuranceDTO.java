package com.example.demo.models.dto;

import com.example.demo.models.enums.InsuranceType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class InsuranceDTO {

    //pole pro uživatele
    private Long userId;

    @NotNull(message = "You must choose type of insurance.")
    private InsuranceType insuranceType;//výběr typu pojištění (přes enum)

    @NotNull(message = "Sum insured is required")
    @Positive(message = "Sum insured must be positive number")
    private int amount; //pojistná částka - musí být zadána

    //@NotBlank(message = "Insured item is required")
    private String insuredItem; // pojištěný předmět či osobs (nemusí být zadána)

    @NotNull(message = "Insurance start date is required")
    @FutureOrPresent(message = "Insurance start date must be today or in future")
    private LocalDate startDate; //začátek pojištění - dnes nebo někdy v budoucnu

    @NotNull(message = "Insurance end date is required")
    @Future(message = "Insurance end date must be in future")
    private LocalDate endDate; //konec pojištění todo - nastavit aby byl aspoň rok v budoucnosti od startu pojištění

    //region: gettery a settery


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getInsuredItem() {
        return insuredItem;
    }

    public void setInsuredItem(String insuredItem) {
        this.insuredItem = insuredItem;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    //konec regionu
}
