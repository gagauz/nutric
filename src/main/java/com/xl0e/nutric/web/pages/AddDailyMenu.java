package com.xl0e.nutric.web.pages;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.DailyMenuDao;
import com.xl0e.nutric.dao.MealDao;
import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.Meal;

public class AddDailyMenu {

    @Inject
    protected DailyMenuDao dailyMenuDao;

    @Inject
    protected MealDao mealDao;

    @Property
    private DailyMenu object;

    @Property
    private Meal meal;

    void onActivate(DailyMenu edit) {
        object = edit;
    }

    Object onSuccessFromForm() {
        dailyMenuDao.save(object);
        return Index.class;
    }

    public List<Meal> getMeals() {
        if (null != object && null != object.getId()) {
            return mealDao.findByFilter(EntityFilterBuilder.eq("parent", object));
        }
        return Collections.emptyList();
    }
}
