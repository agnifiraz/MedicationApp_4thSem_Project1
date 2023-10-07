package com.example.agnitapaul_project1;

public class Medication {

    private String medicationName;
    private String time_;
    private String day_;
    private String description_;

    public Medication(String medicationName, String time_, String day_,String description_) {
        this.medicationName = medicationName;
        this.time_ = time_;
        this.day_ = day_;
        this.description_= description_;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getTime_() {
        return time_;
    }

    public void setTime_(String time_) {
        this.time_ = time_;
    }

    public String getDay_() {
        return day_;
    }

    public void setDay_(String day_) {
        this.day_ = day_;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }
}
