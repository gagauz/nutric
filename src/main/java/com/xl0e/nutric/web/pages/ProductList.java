package com.xl0e.nutric.web.pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import com.xl0e.nutric.dao.ProductDao;
import com.xl0e.nutric.model.Product;
import com.xl0e.util.CollectionUtils;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Checkbox;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;

public class ProductList {

    @Inject
    protected ProductDao productDao;

    @Inject
    private AlertManager alertManager;

    @Inject
    private Messages messages;

    @Component
    private Form gridForm;

    @Component
    private Checkbox check;

    @Property
    private Product product;

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
        return productDao.findAll();
    }

    public void onDrop(Product product) {
        productDao.delete(product);
        alertManager.success(messages.get("product-removed-ok"));
    }

    void onSubmitFromGridForm() {
        if (!getChecked().isEmpty()) {
            int removed = productDao.removeById(getChecked());
            alertManager.success(messages.format("removed-x-products", removed));
        }
    }
}
