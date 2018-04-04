package com.xl0e.nutric.dao;

import org.springframework.stereotype.Service;

import com.xl0e.nutric.model.DailyMenu;
import com.xl0e.util.C;

@Service
public class DailyMenuDao extends AbstractDao<Integer, DailyMenu> {

    @Override
    public void delete(DailyMenu entity) {
        C.emptyIfNull(entity.getMeals()).removeIf(m -> {
            if (m.getDailyMenus().size() > 1) {
                m.getDailyMenus().remove(entity);
                return true;
            }
            return false;
        });
        super.delete(entity);
    }
}
