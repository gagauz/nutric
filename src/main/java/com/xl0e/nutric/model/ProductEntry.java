package com.xl0e.nutric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xl0e.hibernate.model.Model;

@Entity
@Table
public class ProductEntry extends Model {
    private Meal meal;
    private Product    product;
    private float      weight;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal parent) {
        this.meal = parent;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
