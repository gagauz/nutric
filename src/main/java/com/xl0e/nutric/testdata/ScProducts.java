package com.xl0e.nutric.testdata;

import com.xl0e.nutric.services.CsvImportService;
import com.xl0e.testdata.DataBaseScenario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScProducts extends DataBaseScenario {

    @Autowired
    private CsvImportService csvImportService;

    @Override
    protected void execute() {
        csvImportService.importProducts(getClass().getClassLoader().getResourceAsStream("products.csv"));
    }

}
