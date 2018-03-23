package com.xl0e.nutric.config;

import com.xl0e.util.IAppProperty;

public enum AppProperties implements IAppProperty<AppProperties> {
    JDBC_USERNAME("b4f"),
    JDBC_PASSWORD("office"),
    JDBC_URL("jdbc:mysql://localhost:3306/nutric"),
    FILL_TEST_DATA("false"),
    SOLR_CLOUD_NODES("");

    private final String defaultValue;

    AppProperties(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return getString();
    }
}
