
package com.xl0e.nutric.web.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import com.xl0e.nutric.model.Meal;
import com.xl0e.nutric.model.ProductEntry;

public class MealDiv {
    @Parameter
    @Property
    private Meal meal;

    @Property
    private ProductEntry entry;
}
