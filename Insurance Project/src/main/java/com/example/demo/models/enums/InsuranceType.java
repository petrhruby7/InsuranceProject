package com.example.demo.models.enums;

public enum InsuranceType {
    /**
     * Tipy pojištění
     */
    TRAVEL("Travel Insurance"),
    PROPERTY("Property Insurance"),
    VEHICLE("Vehicle Insurance"),
    LIFE("Life Insurance"),
    HEALTH("Health Insurance"),
    LIABILITY("Liability Insurance");

    /**
     * atribut - zobraz jméno
     */
    private final String displayName;

    /**
     * konstruktor
     * @param displayName
     */
    InsuranceType(String displayName) {
        this.displayName = displayName;
    }
}
