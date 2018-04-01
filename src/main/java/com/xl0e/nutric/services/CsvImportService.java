package com.xl0e.nutric.services;

import static com.xl0e.nutric.testdata.ProductColumns.*;

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
                Product product = productsMap.getOrDefault(tokens[0], new Product());
                product.setName(NAME.parse(tokens));

                product.setProteins(PROTEINS.parse(tokens));
                product.setFats(FATS.parse(tokens));
                product.setCarbohydrates(CARBOHYDRATES.parse(tokens));
                product.setCaloricValue(CALORIES.parse(tokens));

                product.getVitamins().setA(V_A.parse(tokens));
                product.getVitamins().setB1(V_B1.parse(tokens));
                product.getVitamins().setB2(V_B2.parse(tokens));
                product.getVitamins().setB3(V_B3.parse(tokens));
                product.getVitamins().setB5(V_B5.parse(tokens));
                product.getVitamins().setB6(V_B6.parse(tokens));
                product.getVitamins().setB7(V_B7.parse(tokens));
                product.getVitamins().setB9(V_B9.parse(tokens));
                product.getVitamins().setB12(V_B12.parse(tokens));
                product.getVitamins().setC(V_C.parse(tokens));
                product.getVitamins().setD(V_D.parse(tokens));
                product.getVitamins().setE(V_E.parse(tokens));
                product.getVitamins().setK(V_K.parse(tokens));

                product.getMinerals().setCa(M_Ca.parse(tokens));
                product.getMinerals().setMg(M_Mg.parse(tokens));
                product.getMinerals().setMn(M_Mn.parse(tokens));
                product.getMinerals().setSe(M_Se.parse(tokens));
                product.getMinerals().setZn(M_Zn.parse(tokens));
                product.getMinerals().setCu(M_Cu.parse(tokens));
                product.getMinerals().setJ(M_J.parse(tokens));
                product.getMinerals().setK(M_K.parse(tokens));
                product.getMinerals().setNa(M_Na.parse(tokens));
                product.getMinerals().setP(M_P.parse(tokens));
                product.getMinerals().setCl(M_Cl.parse(tokens));
                product.getMinerals().setF(M_F.parse(tokens));
                product.getMinerals().setCr(M_Cr.parse(tokens));

                list.add(product);
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
