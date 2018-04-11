package com.xl0e.nutric.web.pages;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.web.services.security.Secured;

import com.xl0e.nutric.dao.DailyMenuDao;
import com.xl0e.nutric.dao.MealDao;
import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.Meal;
import com.xl0e.nutric.model.MenuGroup;

@Secured("user")
public class AddDailyMenu {

    @Inject
    protected DailyMenuDao dailyMenuDao;

    @Inject
    protected MealDao mealDao;

    @PageActivationContext(index = 1)
    @Property
    protected DailyMenu object;

    @Property
    protected Meal meal;

    @PageActivationContext(index = 0)
    @Property
    protected MenuGroup menuGroup;

    public Object onPassivate() {
        return new Object[] { menuGroup, object };
    }

    public Object onSuccessFromForm() {
        object.setOwner(menuGroup);
        dailyMenuDao.save(object);
        return Index.class;
    }

    public void onDrop(Meal meal) {
        object.getMeals().remove(meal);
        dailyMenuDao.save(object);
    }
}
