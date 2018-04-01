package com.xl0e.nutric.testdata;

import static com.xl0e.nutric.testdata.RequirementColumns.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl0e.nutric.dao.RequirementDao;
import com.xl0e.nutric.model.Requirement;
import com.xl0e.testdata.DataBaseScenario;

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
                req.setName(NAME.parse(tokens));
                req.setGender(GENDER.parse(tokens));
                req.setAgeMax(AGE_MIN.parse(tokens));
                req.setAgeMax(AGE_MAX.parse(tokens));
                req.setRelative(RELATIVE.parse(tokens));

                req.setProteins(PROTEINS.parse(tokens));
                req.setFats(FATS.parse(tokens));
                req.setCarbohydrates(CARBOHYDRATES.parse(tokens));
                req.setCaloricValue(CALORIES.parse(tokens));

                req.getVitamins().setA(V_A.parse(tokens));
                req.getVitamins().setB1(V_B1.parse(tokens));
                req.getVitamins().setB2(V_B2.parse(tokens));
                req.getVitamins().setB3(V_B3.parse(tokens));
                req.getVitamins().setB5(V_B5.parse(tokens));
                req.getVitamins().setB6(V_B6.parse(tokens));
                req.getVitamins().setB7(V_B7.parse(tokens));
                req.getVitamins().setB9(V_B9.parse(tokens));
                req.getVitamins().setB12(V_B12.parse(tokens));
                req.getVitamins().setC(V_C.parse(tokens));
                req.getVitamins().setD(V_D.parse(tokens));
                req.getVitamins().setE(V_E.parse(tokens));
                req.getVitamins().setK(V_K.parse(tokens));

                req.getMinerals().setCa(M_Ca.parse(tokens));
                req.getMinerals().setMg(M_Mg.parse(tokens));
                req.getMinerals().setMn(M_Mn.parse(tokens));
                req.getMinerals().setSe(M_Se.parse(tokens));
                req.getMinerals().setZn(M_Zn.parse(tokens));
                req.getMinerals().setCu(M_Cu.parse(tokens));
                req.getMinerals().setJ(M_J.parse(tokens));
                req.getMinerals().setK(M_K.parse(tokens));
                req.getMinerals().setNa(M_Na.parse(tokens));
                req.getMinerals().setP(M_P.parse(tokens));
                req.getMinerals().setCl(M_Cl.parse(tokens));
                req.getMinerals().setF(M_F.parse(tokens));
                req.getMinerals().setCr(M_Cr.parse(tokens));
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
