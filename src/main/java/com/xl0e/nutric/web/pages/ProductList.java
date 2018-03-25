package com.xl0e.nutric.web.pages;

import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;

import com.xl0e.nutric.dao.ProductDao;
import com.xl0e.nutric.model.Product;
import com.xl0e.nutric.services.CsvImportService;

public class ProductList {
    @Inject
    protected ProductDao productDao;

    @Inject
    private CsvImportService csvImportService;

    @Property
    private Product product;

    public List getProducts() {
        return productDao.findAll();
    }

}
