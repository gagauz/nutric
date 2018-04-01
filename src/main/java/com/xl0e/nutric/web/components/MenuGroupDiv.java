
package com.xl0e.nutric.web.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.xl0e.nutric.dao.MenuGroupDao;
import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.nutric.model.MenuGroup;

public class MenuGroupDiv {
    @Parameter
    @Property
    private MenuGroup menuGroup;

    @Property
    private DailyMenu dailyMenu;

    @Inject
    private MenuGroupDao menuGroupDao;

    void onDrop(MenuGroup drop) {
        menuGroupDao.deleteById(drop.getId());
    }
}
