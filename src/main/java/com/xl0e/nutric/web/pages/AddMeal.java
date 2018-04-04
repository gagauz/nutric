package com.xl0e.nutric.web.pages;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.web.services.security.Secured;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.DailyMenuDao;
import com.xl0e.nutric.dao.MealDao;
import com.xl0e.nutric.dao.ProductEntryDao;
import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.Meal;
import com.xl0e.nutric.model.ProductEntry;

@Secured("user")
public class AddMeal {

    @Inject
    protected MealDao mealDao;

    @Inject
    protected DailyMenuDao dailyMenuDao;

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
        menu.addMeal(object);
        dailyMenuDao.saveAll(menu, object);
        return Index.class;
    }

    public List<ProductEntry> getEntries() {
        if (null != object && null != object.getId()) {
            return productEntryDao.findByFilter(EntityFilterBuilder.eq("owner", object));
        }
        return Collections.emptyList();
    }
}
