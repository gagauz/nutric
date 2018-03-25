package com.xl0e.nutric.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.xl0e.hibernate.model.Model;

@SuppressWarnings("serial")
@MappedSuperclass
public class NutritientValue extends Model {
    private Origin origin;
    private float proteins;
    private float carbohydrates;
    private float fats;
    private float caloricValue;
    private Minerals minerals = new Minerals();
    private Vitamins vitamins = new Vitamins();
    private Date created;
    private Date updated;

    @Enumerated(EnumType.STRING)
    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    @Column(nullable = false)
    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    @Column(nullable = false)
    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Column(nullable = false)
    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    @Column(nullable = false)
    public float getCaloricValue() {
        return caloricValue;
    }

    public void setCaloricValue(float caloricValue) {
        this.caloricValue = caloricValue;
    }

    @Embedded
    public Minerals getMinerals() {
        return minerals;
    }

    public void setMinerals(Minerals minerals) {
        this.minerals = minerals;
    }

    @Embedded
    public Vitamins getVitamins() {
        return vitamins;
    }

    public void setVitamins(Vitamins vitamins) {
        this.vitamins = vitamins;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Transient
    public void add(Product product, float ratio) {
        caloricValue += caloricValue + product.getCaloricValue() * ratio;
        proteins += proteins + product.getProteins() * ratio;
        fats += fats + product.getFats() * ratio;
        carbohydrates += carbohydrates + product.getCarbohydrates() * ratio;
        vitamins.add(product.getVitamins(), ratio);
        minerals.add(product.getMinerals(), ratio);
    }

    @Transient
    public void percent(Requirement required) {
        caloricValue = 100 * caloricValue / required.getCaloricValue();
        proteins = 100 * proteins / required.getProteins();
        fats = 100 * fats / required.getFats();
        carbohydrates = 100 * carbohydrates / required.getCarbohydrates();
        vitamins.percent(required.getVitamins());
        minerals.percent(required.getMinerals());

    }

}
