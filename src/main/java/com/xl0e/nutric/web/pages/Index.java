package com.xl0e.nutric.web.pages;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.MenuGroupDao;
import com.xl0e.nutric.model.Account;
import com.xl0e.nutric.model.MenuGroup;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.ApplicationStateManager;

public class Index {

    @Inject
    private MenuGroupDao menuGroupDao;

    @Inject
    private ApplicationStateManager applicationStateManager;

    @Property
    private MenuGroup row;

    public List getmenuGroups() {

        Account account = applicationStateManager.getIfExists(Account.class);
        if (null == account) {
            return Collections.emptyList();
        }

        return menuGroupDao.findByFilter(EntityFilterBuilder.eq("account.id", account.getId()));

    }

    Object onAdd() {
        return AddMenuGroup.class;
    }

    Object onEdit() {
        return AddMenuGroup.class;
    }

    Object onDrop() {
        return AddMenuGroup.class;
    }
}
