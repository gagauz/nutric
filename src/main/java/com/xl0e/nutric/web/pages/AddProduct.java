package com.xl0e.nutric.web.pages;

import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.web.services.RedirectLink;
import org.apache.tapestry5.web.services.security.Secured;

import com.xl0e.nutric.dao.ProductDao;
import com.xl0e.nutric.model.Product;

@Secured("user")
public class AddProduct {

    @Inject
    protected ProductDao productDao;

    @PageActivationContext
    @Property
    private Product object;

    public Object onSuccessFromForm() {
        productDao.save(object);
        return RedirectLink.forPage(ProductList.class);
    }

}
