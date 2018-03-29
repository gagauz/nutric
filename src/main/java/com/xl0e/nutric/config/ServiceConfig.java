package com.xl0e.nutric.config;

import com.xl0e.nutric.services.CsvImportService;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { CsvImportService.class })
public class ServiceConfig {

}
