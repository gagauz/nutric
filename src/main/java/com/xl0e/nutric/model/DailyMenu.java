package com.xl0e.nutric.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xl0e.hibernate.model.Model;

/**
 * E.g. Monday, Tuesday ...
 *
 */
@Entity
@Table(name = "DAILY_MENU")
public class DailyMenu extends Model {
    private static final long serialVersionUID = -468801928390080969L;
    private String name;
    private List<Meal> meals;
    private MenuGroup menuGroup;

    @Column(nullable = false)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuGroup_id", nullable = false)
    public MenuGroup getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
    }

}
