package com.example.demo.data.entities;

import com.example.demo.models.enums.InsuranceType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Insurance")
public class InsuranceEntity{
    /**
     * Id pojištění: samo se generuje
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceId;
    /**
     * navazujeme na existující Entity - UserEntity
     * InsuranceEntity je více na jednu UserEntity
     */
    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    private  UserEntity userEntity;
    /**
     * Typ pojištění - vybírá se bude přes ENUM
     * musí být vyplněno
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsuranceType insuranceType;
    /**
     * pojistná částka
     * musí být vyplněno
     */
    @Column(nullable = false)
    private int amount;
    /**
     * předmět pojištění
     * NEMUSÍ být vyplněno
     */
    @Column
    private String insuredItem;
    /**
     * datum vzniku pojištění
     * musí být vyplněno
     */
    @Column(nullable = false)
    private LocalDate startDate;
    /**
     * konec vniku pojištění
     */
    @Column(nullable = false)
    private LocalDate endDate;

    //sekce Gettery a settery

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    //getter navíc - získá ID uživatele
    public Long getUserId(){
        if(getUserEntity()==null){
            return null;
        }
        return this.userEntity.getUserId();
    }

    //konec sekce Gettery a Settery


}
