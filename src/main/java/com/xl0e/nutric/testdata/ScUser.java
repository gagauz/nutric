package com.xl0e.nutric.testdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl0e.nutric.dao.AccountDao;
import com.xl0e.nutric.model.Account;
import com.xl0e.testdata.DataBaseScenario;

@Service
public class ScUser extends DataBaseScenario {

    @Autowired
    private AccountDao userDao;

    @Override
    protected void execute() {
        Account user = new Account();
        user.setUsername("111");
        user.setPassword("111");
        userDao.save(user);
    }

}
