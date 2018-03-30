package com.xl0e.nutric.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xl0e.hibernate.model.Model;

/**
 * E.g. Breakfast, Dinner ...
 *
 */
@Entity
@Table(name = "MEAL")
public class Meal extends Model {
    private static final long serialVersionUID = 1814994028424805405L;
    private String name;
    private DailyMenu parent;
    private List<ProductEntry> entries;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    public DailyMenu getParent() {
        return parent;
    }

    public void setParent(DailyMenu parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "meal")
    public List<ProductEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ProductEntry> setEntries) {
        this.entries = setEntries;
    }
}
