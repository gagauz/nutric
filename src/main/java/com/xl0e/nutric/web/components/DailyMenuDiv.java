
package com.xl0e.nutric.web.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.Meal;

public class DailyMenuDiv {
    @Parameter
    @Property
    private DailyMenu dailyMenu;

    @Property
    private Meal meal;
}
