package com.xl0e.nutric.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xl0e.nutric.testdata.ScUser;
import com.xl0e.nutric.testdata.TestDataInitializer;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackageClasses = { ScUser.class })
public class TestDataConfig {
    @Bean
    public TestDataInitializer testDataInitializer() {
        return new TestDataInitializer();
    }
}
