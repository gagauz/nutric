package com.xl0e.nutric.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xl0e.hibernate.model.Model;

/**
 * E.g. Monday, Tuesday ...
 *
 */
@Entity
@Table
public class DailyMenu extends Model {
    private String name;
    private List<Meal> meals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "parent")
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> children) {
        this.meals = children;
    }

}
