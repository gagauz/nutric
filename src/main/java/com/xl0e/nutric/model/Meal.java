package com.xl0e.nutric.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xl0e.hibernate.model.Model;

/**
 * E.g. Breakfast, Dinner ...
 *
 */
@Entity
@Table(name = "MEAL")
public class Meal extends Model implements Owned, Cloneable {
    private static final long serialVersionUID = 1814994028424805405L;
    private String name;
    @JsonIgnore
    private MenuGroup owner;
    private List<ProductEntry> entries;
    private List<DailyMenu> dailyMenus;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    public MenuGroup getOwner() {
        return owner;
    }

    public void setOwner(MenuGroup owner) {
        this.owner = owner;
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    public List<ProductEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ProductEntry> setEntries) {
        this.entries = setEntries;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    public List<DailyMenu> getDailyMenus() {
        return dailyMenus;
    }

    public void setDailyMenus(List<DailyMenu> dailyMenus) {
        this.dailyMenus = dailyMenus;
    }
}
