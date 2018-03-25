package com.xl0e.nutric.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xl0e.hibernate.config.AbstractHibernateConfig;
import com.xl0e.nutric.dao.AbstractDao;
import com.xl0e.nutric.services.CsvImportService;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackageClasses = { AbstractDao.class, CsvImportService.class })
public class HibernateConfigImpl extends AbstractHibernateConfig {

    @Override
    @Bean(autowire = Autowire.BY_NAME)
    public DataSource dataSource() {
        return new DataSourceBuilder().build();
    }

    @Override
    @Bean(autowire = Autowire.BY_NAME)
    public FactoryBean<SessionFactory> sessionFactory() {
        return new SessionFactoryBean();
    }
}
