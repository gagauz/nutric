package com.xl0e.nutric.web.pages;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.ApplicationStateManager;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.MenuGroupDao;
import com.xl0e.nutric.model.Account;
import com.xl0e.nutric.model.MenuGroup;

@Import(module = "bootstrap/collapse")
public class Index {

    @Inject
    private MenuGroupDao menuGroupDao;

    @Inject
    private ApplicationStateManager applicationStateManager;

    @Property
    private MenuGroup row;

    public List getMenuGroups() {

        Account account = applicationStateManager.getIfExists(Account.class);
        if (null == account) {
            return Collections.emptyList();
        }

        return menuGroupDao.findByFilter(EntityFilterBuilder.eq("owner.id", account.getId()));

    }

    Object onAdd() {
        return AddMenuGroup.class;
    }

    Object onEdit() {
        return AddMenuGroup.class;
    }

}
