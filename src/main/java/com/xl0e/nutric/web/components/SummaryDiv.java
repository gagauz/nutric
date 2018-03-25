package com.xl0e.nutric.web.components;

import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.RequirementDao;
import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.Meal;
import com.xl0e.nutric.model.NutritientValue;
import com.xl0e.nutric.model.ProductEntry;
import com.xl0e.nutric.model.Requirement;

public class SummaryDiv {

    @Inject
    private RequirementDao requirementDao;

    @Property
    private NutritientValue summary;

    @Parameter(allowNull = false, required = true)
    @Property
    private DailyMenu dailyMenu;

    void beginRender() {
        final NutritientValue summary = new NutritientValue();
        for (Meal meal : dailyMenu.getMeals()) {
            for (ProductEntry e : meal.getEntries()) {
                summary.add(e.getProduct(), e.getWeight() / 100);
            }
        }
        float age = 1 + 10.0f / 12;
        List<Requirement> reqs = requirementDao.findByFilter(EntityFilterBuilder.le("ageMin", age).gt("ageMax", age));
        final Requirement req = reqs.iterator().next();
        summary.percent(req);
        this.summary = summary;
    }
}
