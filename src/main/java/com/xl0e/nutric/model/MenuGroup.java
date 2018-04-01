package com.xl0e.nutric.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xl0e.hibernate.model.Model;

@Entity
@Table(name = "MENU_GROUP")
public class MenuGroup extends Model implements Owned {
    private static final long serialVersionUID = -9000587612914858595L;
    private String name;
    private Account owner;
    private float weight;
    private float age;
    private List<DailyMenu> dailyMenus;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account account) {
        this.owner = account;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    @OneToMany(targetEntity = DailyMenu.class, fetch = FetchType.LAZY, mappedBy = "owner")
    public List<DailyMenu> getDailyMenus() {
        return dailyMenus;
    }

    public void setDailyMenus(List<DailyMenu> dailyMenus) {
        this.dailyMenus = dailyMenus;
    }

}
