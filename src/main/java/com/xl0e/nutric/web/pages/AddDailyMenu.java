package com.xl0e.nutric.web.pages;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.DailyMenuDao;
import com.xl0e.nutric.dao.MealDao;
import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.Meal;
import com.xl0e.nutric.model.MenuGroup;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.web.services.security.Secured;

@Secured("user")
public class AddDailyMenu {

    @Inject
    protected DailyMenuDao dailyMenuDao;

    @Inject
    protected MealDao mealDao;

    @Property
    private DailyMenu object;

    @Property
    private Meal meal;

    @Property
    private MenuGroup menuGroup;

    void onActivate(Object[] ctx) {
        this.menuGroup = (MenuGroup) ctx[0];
        this.object = null;
    }

    Object onPassivate() {
        return menuGroup;
    }

    Object onSuccessFromForm() {
        object.setMenuGroup(menuGroup);
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
