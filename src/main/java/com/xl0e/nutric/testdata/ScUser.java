package com.xl0e.nutric.testdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl0e.nutric.dao.AccountDao;
import com.xl0e.nutric.dao.ProductDao;
import com.xl0e.nutric.dao.MealDao;
import com.xl0e.nutric.dao.RequirementDao;
import com.xl0e.nutric.model.Account;
import com.xl0e.nutric.model.Product;
import com.xl0e.testdata.DataBaseScenario;

@Service
public class ScUser extends DataBaseScenario {

    @Autowired
    private AccountDao      userDao;

    @Autowired
    private ProductDao      productDao;

    @Autowired
    private MealDao   productSetDao;

    @Autowired
    private RequirementDao requirementsDao;

    @Override
    protected void execute() {
        Account user = new Account();
        user.setUsernameHash("111");
        user.setPasswordHash("111");
        userDao.save(user);

        createProduct("Хлеб", 10.0f, 70.0f, 5.0f, 400000.0f);
        createProduct("Масло", 10.0f, 70.0f, 5.0f, 400000.0f);
        createProduct("Кефир", 10.0f, 70.0f, 5.0f, 400000.0f);
        createProduct("Молоко", 10.0f, 70.0f, 5.0f, 400000.0f);
        createProduct("Овсянка", 10.0f, 70.0f, 5.0f, 400000.0f);
    }

    private void createProduct(String name, float proteins, float carbohydrates, float fats, float caloricValue) {
        Product product = new Product();
        product.setName(name);
        product.setProteins(proteins);
        product.setCarbohydrates(carbohydrates);
        product.setFats(fats);
        product.setCaloricValue(caloricValue);
        productDao.save(product);

    }

}
