package com.xl0e.nutric.web.components;

import javax.inject.Inject;

import org.apache.tapestry5.web.components.FileUploadForm;

import com.xl0e.nutric.services.CsvImportService;

public class ProductUploadForm extends FileUploadForm {
    @Inject
    private CsvImportService csvImportService;

    @Override
    public void onSuccessFromUploadForm() {
        try {
            csvImportService.importProducts(fileValue.getStream());
            alertManager.success(messages.get("file-imported-ok"));
        } catch (Exception e) {
            alertManager.error(messages.get("file-imported-error"));
        }
    }
}
