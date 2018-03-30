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
public class MenuGroup extends Model {
    private static final long serialVersionUID = -9000587612914858595L;
    private String name;
    private Account account;
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    @OneToMany(targetEntity = DailyMenu.class, fetch = FetchType.LAZY, mappedBy = "menuGroup")
    public List<DailyMenu> getDailyMenus() {
        return dailyMenus;
    }

    public void setDailyMenus(List<DailyMenu> dailyMenus) {
        this.dailyMenus = dailyMenus;
    }

}
