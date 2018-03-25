package com.xl0e.nutric.web.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.web.services.RedirectLink;

import com.xl0e.nutric.model.Product;

public class AddProduct extends ProductList {

    @Property
    private Product object;

    void onActivate(Product edit) {
        object = edit;
    }

    Object onSuccessFromForm() {
        productDao.save(object);
        return RedirectLink.forPage(ProductList.class);
    }

}
