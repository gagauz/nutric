package com.xl0e.nutric.testdata;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.xl0e.testdata.DataBaseScenario;

public class TestDataInitializer {

    @Autowired
    private DataBaseScenario[] scenarios;

    @Autowired
    private TransactionWrapper transactionWrapper;

    @PostConstruct
    public void init() {
        transactionWrapper.wrap(() -> execute());
    }

    public void execute() {
        for (DataBaseScenario scenario : scenarios) {
            scenario.run();
        }
    }

}
