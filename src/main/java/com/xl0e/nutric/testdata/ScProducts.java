package com.xl0e.nutric.testdata;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl0e.nutric.services.CsvImportService;
import com.xl0e.testdata.DataBaseScenario;

@Service
public class ScProducts extends DataBaseScenario {

    @Autowired
    private CsvImportService csvImportService;

    @Override
    protected void execute() {
        InputStream is = getClass().getResourceAsStream("products.csv");
        if (null == is) {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("products.csv");
        }
        csvImportService.importProducts(is);
    }

}
