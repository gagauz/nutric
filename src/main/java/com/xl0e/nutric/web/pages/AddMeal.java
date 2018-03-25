package com.xl0e.nutric.web.pages;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.web.services.RedirectLink;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.MealDao;
import com.xl0e.nutric.dao.ProductEntryDao;
import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.Meal;
import com.xl0e.nutric.model.ProductEntry;

public class AddMeal {

    @Inject
    protected MealDao mealDao;

    @Inject
    protected ProductEntryDao productEntryDao;

    @Property
    private Meal object;

    @Property
    private ProductEntry entry;

    @Property
    private DailyMenu menu;

    void onActivate(DailyMenu menu, Meal edit) {
        this.menu = menu;
        object = edit;
    }

    void onActivate(DailyMenu menu) {
        this.menu = menu;
    }

    Object onPassivate() {
        return menu;
    }

    Object onSuccessFromForm() {
        object.setParent(menu);
        mealDao.save(object);
        return RedirectLink.forPage(AddDailyMenu.class, menu);
    }

    public List<ProductEntry> getEntries() {
        if (null != object && null != object.getId()) {
            return productEntryDao.findByFilter(EntityFilterBuilder.eq("meal", object));
        }
        return Collections.emptyList();
    }
}
