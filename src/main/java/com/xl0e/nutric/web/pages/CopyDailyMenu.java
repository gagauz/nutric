package com.xl0e.nutric.web.pages;

import org.apache.tapestry5.web.services.security.Secured;

import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.util.C;

@Secured("user")
public class CopyDailyMenu extends AddDailyMenu {

    @Override
    public Object onSuccessFromForm() {
        try {
            DailyMenu clone = dailyMenuDao.copy(object);
            if (clone.getName().equals(object.getName())) {
                clone.setName(clone.getName() + " (1)");
            }
            clone.setId(null);
            clone.setOwner(object.getOwner());
            C.emptyIfNull(clone.getMeals()).forEach(m -> {
                m.setId(null);
                m.setOwner(clone);
                C.emptyIfNull(m.getEntries()).forEach(e -> {
                    e.setId(null);
                    e.setOwner(m);
                });
            });

            dailyMenuDao.save(clone);
            return Index.class;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
