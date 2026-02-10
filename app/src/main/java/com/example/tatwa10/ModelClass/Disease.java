package com.example.tatwa10.ModelClass;

public class Disease {
    private String name;
    private String category; // Minor or Major
    private String symptoms;
    private String precautions;
    private String treatment;
    private int iconResId;

    public Disease(String name, String category, String symptoms, String precautions, String treatment, int iconResId) {
        this.name = name;
        this.category = category;
        this.symptoms = symptoms;
        this.precautions = precautions;
        this.treatment = treatment;
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getPrecautions() {
        return precautions;
    }

    public String getTreatment() {
        return treatment;
    }

    public int getIconResId() {
        return iconResId;
    }
}
