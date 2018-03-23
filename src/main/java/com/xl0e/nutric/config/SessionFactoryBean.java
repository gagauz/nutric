package com.xl0e.nutric.config;

import static org.hibernate.cfg.AvailableSettings.*;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.tool.schema.Action;
import org.hibernate.type.StringType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.xl0e.nutric.model.Account;
import com.xl0e.util.C;

public class SessionFactoryBean extends LocalSessionFactoryBean {

    private static final Map<String, String> JDBC_DIALECT_MAP = C.newHashMap();
    static {
        JDBC_DIALECT_MAP.put("mysql", "com.xl0e.hibernate.utils.MySQL5InnoDBDialect2");
        JDBC_DIALECT_MAP.put("postgresql", "org.hibernate.dialect.PostgreSQL9Dialect");
    }

    public SessionFactoryBean() {
        setPackagesToScan(Account.class.getPackage().getName());
        setAnnotatedPackages(new String[] { Account.class.getPackage().getName() });
        Properties properties = new Properties();
        properties.put(DIALECT, JDBC_DIALECT_MAP.get(AppProperties.JDBC_URL.toString().split(":")[1]));
        properties.put(SHOW_SQL, false);
        properties.put(FORMAT_SQL, false);
        properties.put(USE_SQL_COMMENTS, false);
        if (AppProperties.FILL_TEST_DATA.getBoolean()) {
            properties.put(HBM2DDL_AUTO, Action.CREATE_DROP);
        } else {
            properties.put(HBM2DDL_AUTO, "none");
        }
        properties.put(STATEMENT_BATCH_SIZE, 50);
        properties.put(STATEMENT_BATCH_SIZE, 50);
        properties.put("hibernate.validator.autoregister_listeners", "create");
        properties.put(USE_REFLECTION_OPTIMIZER, true);
        properties.put(DEFAULT_BATCH_FETCH_SIZE, 50);
        properties.put("current_session_context_class", "thread");
        properties.put("connection.characterEncoding", "utf-8");
        properties.put("hibernate.connection.charset", "utf8");
        properties.put("hibernate.connection.characterEncoding", "utf8");
        properties.put("hibernate.connection.useUnicode", "true");
        setHibernateProperties(properties);
    }

    @Override
    public void afterPropertiesSet() throws IOException {
        super.afterPropertiesSet();
        getConfiguration().addSqlFunction("bit_or", new StandardSQLFunction("bit_or", new StringType()));
    }
}
