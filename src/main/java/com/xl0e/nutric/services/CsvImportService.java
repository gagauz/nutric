package com.xl0e.nutric.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl0e.nutric.dao.ProductDao;
import com.xl0e.nutric.model.Product;

@Service
public class CsvImportService {

    @Autowired
    private ProductDao productDao;

    public void importProducts(InputStream stream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            final Map<String, Product> productsMap = productDao.findAll().stream().collect(Collectors.toMap(Product::getName, p -> p));
            reader.readLine();
            String line;
            List<Product> list = new ArrayList<>(100);
            while (null != (line = reader.readLine())) {
                String[] tokens = line.split(";");
                Product prod = productsMap.getOrDefault(tokens[0], new Product());
                prod.setName(tokens[0]);
                prod.setProteins(parseFloat(tokens[1]));
                prod.setFats(parseFloat(tokens[2]));
                prod.setCarbohydrates(parseFloat(tokens[3]));
                prod.setCaloricValue(parseFloat(tokens[4]));
                prod.getVitamins().setA(parseFloat(tokens[5]));
                prod.getVitamins().setB1(parseFloat(tokens[6]));
                prod.getVitamins().setB2(parseFloat(tokens[7]));
                prod.getVitamins().setB6(parseFloat(tokens[8]));
                prod.getVitamins().setB12(parseFloat(tokens[9]));
                prod.getVitamins().setC(parseFloat(tokens[10]));
                prod.getVitamins().setD(parseFloat(tokens[11]));
                prod.getVitamins().setE(parseFloat(tokens[12]));

                prod.getMinerals().setCa(parseFloat(tokens[13]));
                prod.getMinerals().setMg(parseFloat(tokens[14]));
                prod.getMinerals().setFe(parseFloat(tokens[15]));
                prod.getMinerals().setK(parseFloat(tokens[16]));
                prod.getMinerals().setNa(parseFloat(tokens[17]));
                prod.getMinerals().setP(parseFloat(tokens[18]));
                list.add(prod);
            }
            productDao.saveAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        } // skip 1st line

    }

    private static float parseFloat(String number) {
        return NumberUtils.toFloat(number.replace(',', '.'));
    }

    private static int parseInt(String number) {
        return NumberUtils.toInt(number);
    }

}
