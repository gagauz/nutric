package com.xl0e.nutric.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table
public class Requirement extends NutritientValue {
    private String name;
    private float ageMin;
    private float ageMax;
    private float weightMin;
    private float weightMax;
    private boolean relative;
    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(float ageMin) {
        this.ageMin = ageMin;
    }

    public float getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(float ageMax) {
        this.ageMax = ageMax;
    }

    public float getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(float weightMin) {
        this.weightMin = weightMin;
    }

    public float getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(float weightMax) {
        this.weightMax = weightMax;
    }

    public boolean isRelative() {
        return relative;
    }

    public void setRelative(boolean relative) {
        this.relative = relative;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
