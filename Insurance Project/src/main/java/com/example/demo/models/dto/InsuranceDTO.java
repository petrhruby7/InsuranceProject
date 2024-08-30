package com.example.demo.models.dto;

import com.example.demo.models.enums.InsuranceType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class InsuranceDTO {


    @NotBlank(message = "You must choose type of insurance.")
    private InsuranceType insuranceType;

    @NotNull(message = "Sum insured is required")
    @Positive(message = "Sum insured must be positive number")
    private int amount;

    @NotBlank(message = "Insured item is required")
    private String insuranceItem;

    @NotNull(message = "Insurance start date is required")
    @FutureOrPresent(message = "Insurance start date must be today or in future")
    private LocalDate startDate;

    @NotNull(message = "Insurance end date is required")
    @Future(message = "Insurance end date must be in future")
    private LocalDate endDate;

    //region: gettery a settery


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

    public String getInsuranceItem() {
        return insuranceItem;
    }

    public void setInsuranceItem(String insuranceItem) {
        this.insuranceItem = insuranceItem;
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
