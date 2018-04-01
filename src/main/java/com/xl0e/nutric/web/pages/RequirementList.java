package com.xl0e.nutric.web.pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Checkbox;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.web.services.security.Secured;

import com.xl0e.nutric.dao.RequirementDao;
import com.xl0e.nutric.model.Requirement;
import com.xl0e.util.CollectionUtils;

@Secured({ "user" })
public class RequirementList {

    @Inject
    protected RequirementDao requirementDao;

    @Inject
    private AlertManager alertManager;

    @Inject
    private Messages messages;

    @Component
    private Form gridForm;

    @Component
    private Checkbox check;

    @Property
    private Requirement product;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private Requirement newRequirement;

    private Set<Integer> checked;

    public boolean isChecked() {
        return CollectionUtils.emptyIfNull(checked).contains(product.getId());
    }

    public void setChecked(boolean checked) {
        if (checked) {
            getChecked().add(product.getId());
        } else {
            getChecked().remove(product.getId());
        }
    }

    Set<Integer> getChecked() {
        if (null == checked) {
            checked = new HashSet<>();
        }
        return checked;
    }

    public List getProducts() {
        return requirementDao.findAll();
    }

    public void onDrop(Requirement product) {
        requirementDao.delete(product);
        alertManager.success(messages.get("requirement-removed-ok"));
    }

    void onSubmitFromGridForm() {
        if (!getChecked().isEmpty()) {
            int removed = requirementDao.removeById(getChecked());
            alertManager.success(messages.format("removed-x-products", removed));
        }
    }

    void onAdd() {
        newRequirement = new Requirement();
    }

}
