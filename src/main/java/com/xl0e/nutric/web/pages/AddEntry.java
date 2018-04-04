package com.xl0e.nutric.web.pages;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.SelectModelFactory;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.MealDao;
import com.xl0e.nutric.dao.ProductDao;
import com.xl0e.nutric.dao.ProductEntryDao;
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

    @PageActivationContext(index = 0)
    @Property
    private Meal meal;

    @PageActivationContext(index = 1)
    @Property
    private ProductEntry object;

    Object onSuccessFromForm() {
        object.setOwner(meal);
        productEntryDao.save(object);
        return Index.class;
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
