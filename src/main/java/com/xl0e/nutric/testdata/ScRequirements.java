package com.xl0e.nutric.testdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.xl0e.nutric.dao.RequirementDao;
import com.xl0e.nutric.model.Gender;
import com.xl0e.nutric.model.Requirement;
import com.xl0e.testdata.DataBaseScenario;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScRequirements extends DataBaseScenario {

    @Autowired
    private RequirementDao requirementsDao;

    @Override
    protected void execute() {

        InputStream is = Optional.ofNullable(ClassLoader.getSystemClassLoader().getResourceAsStream("requirements.csv"))
                .orElse(getClass().getClassLoader().getResourceAsStream("requirements.csv"));

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is));
        try {
            reader.readLine();
            String line;
            List<Requirement> list = new ArrayList<>(100);
            while (null != (line = reader.readLine())) {
                String[] tokens = line.split(";");
                Requirement req = new Requirement();
                req.setName(tokens[0]);
                req.setGender(Gender.valueOf(tokens[1]));
                req.setAgeMax(parseInt(tokens[2]));
                req.setAgeMax(parseInt(tokens[3]));
                req.setWeightMin(parseInt(tokens[4]));
                req.setWeightMax(parseInt(tokens[5]));
                req.setRelative(Boolean.parseBoolean(tokens[6]));

                req.setProteins(parseFloat(tokens[7]));
                req.setFats(parseFloat(tokens[8]));
                req.setCarbohydrates(parseFloat(tokens[9]));
                req.setCaloricValue(parseFloat(tokens[10]));
                req.getVitamins().setA(parseFloat(tokens[11]));
                req.getVitamins().setB1(parseFloat(tokens[12]));
                req.getVitamins().setB2(parseFloat(tokens[13]));
                req.getVitamins().setB6(parseFloat(tokens[14]));
                req.getVitamins().setB12(parseFloat(tokens[15]));
                req.getVitamins().setC(parseFloat(tokens[16]));
                req.getVitamins().setD(parseFloat(tokens[17]));
                req.getVitamins().setE(parseFloat(tokens[18]));

                req.getMinerals().setCa(parseFloat(tokens[19]));
                req.getMinerals().setMg(parseFloat(tokens[20]));
                req.getMinerals().setFe(parseFloat(tokens[21]));
                req.getMinerals().setK(parseFloat(tokens[22]));
                req.getMinerals().setNa(parseFloat(tokens[23]));
                req.getMinerals().setP(parseFloat(tokens[24]));
                list.add(req);
            }
            requirementsDao.saveAll(list);
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
