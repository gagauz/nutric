package com.xl0e.nutric.config;

import java.util.Map;

import javax.sql.DataSource;

import com.xl0e.hibernate.utils.StatementInterceptor;
import com.xl0e.util.C;
import com.zaxxer.hikari.HikariConfig;

public class DataSourceBuilder {

    private static final Map<String, String> JDBC_DRIVER_MAP = C.newHashMap();
    static {
        JDBC_DRIVER_MAP.put("mysql", "com.mysql.jdbc.Driver");
        JDBC_DRIVER_MAP.put("postgresql", "org.postgresql.Driver");
    }

    final HikariConfig config = new HikariConfig();

    public DataSourceBuilder() {
        config.setJdbcUrl(AppProperties.JDBC_URL.toString());
        config.setUsername(AppProperties.JDBC_USERNAME.toString());
        config.setPassword(AppProperties.JDBC_PASSWORD.toString());
        config.addDataSourceProperty("ssl", false);
        config.addDataSourceProperty("sslmode", "disable");
        config.addDataSourceProperty("requiressl", false);
        config.addDataSourceProperty("charSet", "UTF-8");
        config.addDataSourceProperty("allowEncodingChanges", false);
        config.addDataSourceProperty("logUnclosedConnections", true);
        config.addDataSourceProperty("autoCloseUnclosedStatements", true);
        config.addDataSourceProperty("client_encoding", "UTF-8");

        final String[] jdbcUrl = AppProperties.JDBC_URL.toString().split(":");
        config.setDriverClassName(JDBC_DRIVER_MAP.get(jdbcUrl[1]));
    }

    public DataSource build() {
        return new TestDataSource();
    }

    public DataSourceBuilder configure(HikariConfig config) {
        config.addDataSourceProperty("statementInterceptors", StatementInterceptor.class.getName());
        config.addDataSourceProperty("statementInterceptor", StatementInterceptor.class.getName());
        config.setAutoCommit(false);
        config.setConnectionTimeout(3000);
        config.setIdleTimeout(300000);
        config.setMaxLifetime(3000);

        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("useUnicode", "true");

        config.addDataSourceProperty("characterEncoding", "UTF-8");
        config.addDataSourceProperty("characterSetResults", "UTF-8");
        config.addDataSourceProperty("useLocalSessionState", "true");

        this.config.setDataSourceProperties(config.getDataSourceProperties());

        return this;
    }

}
