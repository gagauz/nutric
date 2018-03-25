package com.xl0e.nutric.web.pages;

import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;

import com.xl0e.nutric.dao.DailyMenuDao;
import com.xl0e.nutric.model.DailyMenu;

public class Index {

    @Inject
    private DailyMenuDao dailyMenuDao;

    @Property
    private DailyMenu row;

    public List getDailyMenus() {
        return dailyMenuDao.findAll();
    }

    Object onAdd() {
        return AddDailyMenu.class;
    }

    Object onEdit() {
        return AddDailyMenu.class;
    }

    Object onDrop() {
        return AddDailyMenu.class;
    }
}
