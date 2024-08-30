package com.example.demo.models.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class InsuranceDTO {

    private Long userId; //todo: nevim co s tímhle má to být DTO? Chci aby useID bylo použito toho přihlášeného uživatele

    @NotBlank(message = "You must choose type of insurance.")
    private String insuranceType;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
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
