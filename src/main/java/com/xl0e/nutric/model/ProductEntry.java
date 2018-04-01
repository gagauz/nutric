package com.xl0e.nutric.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xl0e.hibernate.model.Model;

@Entity
@Table(name = "PRODUCT_ENTRY")
public class ProductEntry extends Model implements Owned, Cloneable {
    private static final long serialVersionUID = -8973042203316310912L;
    @JsonIgnore
    private Meal owner;
    private Product product;
    private float weight;

    @Override
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    public Meal getOwner() {
        return owner;
    }

    public void setOwner(Meal parent) {
        this.owner = parent;
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

    @Override
    public ProductEntry clone() throws CloneNotSupportedException {
        ProductEntry clone = new ProductEntry();
        clone.setProduct(getProduct());
        clone.setWeight(getWeight());
        return clone;
    }
}
