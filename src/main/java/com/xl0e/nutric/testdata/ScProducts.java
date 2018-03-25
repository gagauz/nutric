package com.xl0e.nutric.testdata;

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
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        csvImportService.importProducts(cl.getResourceAsStream("products.csv"));
    }

}
