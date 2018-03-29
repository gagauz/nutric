package com.xl0e.nutric.hibernate;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.xl0e.hibernate.utils.StatementInterceptor;
import com.xl0e.nutric.config.AppProperties;
import com.xl0e.util.C;

public class TestDataSource extends SimpleDriverDataSource implements DataSource {

    private static final Map<String, Class<? extends java.sql.Driver>> JDBC_DRIVER_MAP = C.newHashMap();
    static {
        JDBC_DRIVER_MAP.put("mysql", com.mysql.jdbc.Driver.class);
        JDBC_DRIVER_MAP.put("postgresql", org.postgresql.Driver.class);
    }

    public TestDataSource() {
        for (Entry e : System.getProperties().entrySet()) {
            System.out.format("%1$40s = %2$s\n", e.getKey(), e.getValue());
        }
        setUrl(AppProperties.JDBC_URL.toString());
        setUsername(AppProperties.JDBC_USERNAME.toString());
        setPassword(AppProperties.JDBC_PASSWORD.toString());
        setDriverClass(JDBC_DRIVER_MAP.get(AppProperties.JDBC_URL.toString().split(":")[1]));
        Properties props = new Properties();
        props.setProperty("cacheServerConfiguration", "true");
        props.setProperty("useUnicode", "true");
        props.setProperty("characterEncoding", "UTF-8");
        props.setProperty("characterSetResults", "UTF-8");
        props.setProperty("useLocalSessionState", "true");
        props.setProperty("statementInterceptors", StatementInterceptor.class.getName());
        props.setProperty("statementInterceptor", StatementInterceptor.class.getName());
        props.setProperty("includeThreadDumpInDeadlockExceptions", "true");
        props.setProperty("logSlowQueries", "true");
        props.setProperty("includeInnodbStatusInDeadlockExceptions", "true");
        props.setProperty("logger", "com.mysql.jdbc.log.Slf4JLogger");
        props.setProperty("dumpQueriesOnException", "true");
        setConnectionProperties(props);
    }
}
