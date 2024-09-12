package com.example.demo.models.enums;

public enum InsuranceType {

    TRAVEL("Travel Insurance"),
    PROPERTY("Property Insurance"),
    VEHICLE("Vehicle Insurance"),
    LIFE("Life Insurance"),
    HEALTH("Health Insurance"),
    LIABILITY("Liability Insurance");

    private final String displayName;

    InsuranceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
