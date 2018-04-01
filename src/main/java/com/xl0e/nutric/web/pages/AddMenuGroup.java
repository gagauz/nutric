package com.xl0e.nutric.web.pages;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.web.services.security.Secured;

import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.MenuGroupDao;
import com.xl0e.nutric.model.Account;
import com.xl0e.nutric.model.MenuGroup;

@Secured("user")
public class AddMenuGroup {

    @Inject
    protected MenuGroupDao menuGroupDao;

    @Property
    private MenuGroup object;

    @Inject
    private ApplicationStateManager applicationStateManager;

    @Property
    private MenuGroup row;

    void onActivate(MenuGroup edit) {
        object = edit;
    }

    Object onSuccessFromForm() {
        object.setOwner(getAccount());
        menuGroupDao.save(object);
        return Index.class;
    }

    public List getMenuGroups() {

        Account account = getAccount();
        if (null == account) {
            return Collections.emptyList();
        }

        return menuGroupDao.findByFilter(EntityFilterBuilder.eq("owner.id", account.getId()));

    }

    private Account getAccount() {
        Account account = applicationStateManager.getIfExists(Account.class);
        return account;
    }

}
