
package com.xl0e.nutric.web.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.xl0e.nutric.dao.DailyMenuDao;
import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.Meal;
import com.xl0e.nutric.web.pages.Index;

public class DailyMenuDiv {
    @Parameter
    @Property
    private DailyMenu dailyMenu;

    @Property
    private Meal meal;

    @Inject
    private DailyMenuDao dailyMenuDao;

    Object onDrop(DailyMenu menu) {
        dailyMenuDao.delete(menu);
        return Index.class;
    }
}
