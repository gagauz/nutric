package com.xl0e.nutric.web.pages;

import com.xl0e.nutric.model.Product;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.web.services.security.Secured;

@Secured("user")
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
