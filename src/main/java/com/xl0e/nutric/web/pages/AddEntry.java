package com.xl0e.nutric.web.pages;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.web.services.RedirectLink;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.MealDao;
import com.xl0e.nutric.dao.ProductDao;
import com.xl0e.nutric.dao.ProductEntryDao;
import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.Meal;
import com.xl0e.nutric.model.ProductEntry;

public class AddEntry {

    @Inject
    protected MealDao mealDao;

    @Inject
    protected ProductEntryDao productEntryDao;

    @Inject
    protected ProductDao productDao;

    @Inject
    protected SelectModelFactory selectModelFactory;

    @Property
    private ProductEntry object;

    @Property
    private Meal meal;

    @Property
    private DailyMenu menu;

    void onActivate(DailyMenu menu, Meal meal, ProductEntry edit) {
        this.menu = menu;
        this.meal = meal;
        object = edit;
    }

    void onActivate(DailyMenu menu, Meal meal) {
        onActivate(menu, meal, null);
    }

    Object onPassivate() {
        return new Object[] { menu, meal };
    }

    Object onSuccessFromForm() {
        object.setMeal(meal);
        productEntryDao.save(object);
        return RedirectLink.forPage(AddMeal.class, menu, meal);
    }

    public List<ProductEntry> getEntries() {
        if (null != object && null != object.getId()) {
            return productEntryDao.findByFilter(EntityFilterBuilder.eq("meal", object));
        }
        return Collections.emptyList();
    }

    public SelectModel getProductModel() {
        return selectModelFactory.create(productDao.findAll(), "name");
    }
}
