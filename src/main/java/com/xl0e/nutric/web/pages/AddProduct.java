package com.xl0e.nutric.web.pages;

import org.apache.tapestry5.annotations.Property;

import com.xl0e.nutric.model.Product;

public class AddProduct {
    //
    // @Inject
    // protected ProductDao productDao;
    //
    @Property
    private Product object;
    //
    // void onActivate() {
    // onActivate(null);
    // }
    //
    // void onActivate(Product edit) {
    // object = edit;
    // }
    //
    // public Object onSuccessFromForm() {
    // productDao.save(object);
    // return RedirectLink.forPage(ProductList.class);
    // }

}
