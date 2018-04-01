package com.xl0e.nutric.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xl0e.hibernate.model.Model;

/**
 * E.g. Monday, Tuesday ...
 *
 */
@Entity
@Table(name = "DAILY_MENU")
public class DailyMenu extends Model implements Owned, Cloneable {
    private static final long serialVersionUID = -468801928390080969L;
    private String name;
    private List<Meal> meals;
    @JsonIgnore
    private MenuGroup owner;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> children) {
        this.meals = children;
    }

    @Override
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    public MenuGroup getOwner() {
        return owner;
    }

    public void setOwner(MenuGroup menuGroup) {
        this.owner = menuGroup;
    }

    @Override
    public DailyMenu clone() throws CloneNotSupportedException {
        DailyMenu clone = new DailyMenu();
        clone.setName(getName());
        clone.setOwner(getOwner());
        return clone;
    }
}
