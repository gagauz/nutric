package com.xl0e.nutric.testdata;

import static com.xl0e.nutric.testdata.Parsers.*;

import com.xl0e.nutric.model.Gender;

public enum RequirementColumns {
    NAME(STRING),
    GENDER(enumParser(Gender.class)),
    AGE_MIN,
    AGE_MAX,
    RELATIVE(BOOL),
    PROTEINS,
    FATS,
    CARBOHYDRATES,
    CALORIES,
    V_A,
    V_B1,
    V_B2,
    V_B3,
    V_B5,
    V_B6,
    V_B7,
    V_B9,
    V_B12,
    V_C,
    V_D,
    V_E,
    V_K,
    M_Ca,
    M_Mg,
    M_Mn,
    M_Se,
    M_Fe,
    M_Zn,
    M_Cu,
    M_J,
    M_K,
    M_Na,
    M_P,
    M_Cl,
    M_F,
    M_Cr;

    private static final String[] EXCEL_COLUMNS = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    private Parser<?> parser;

    RequirementColumns(Parser<?> parser) {
        this.parser = parser;
    }

    RequirementColumns() {
        this(FLOAT);
    }

    public String getParserName() {
        return parser.toString();
    }

    @SuppressWarnings("unchecked")
    public <T> T parse(String[] values) {
        try {
            return (T) parser.parse(values[ordinal()]);
        } catch (ArrayIndexOutOfBoundsException oe) {
            oe.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new IllegalStateException("Ошибка при извлечении значения из колонки #" + ordinal() + " [" + name()
                    + "]: [" + values[ordinal()] + "] : " + e.getMessage(),
                    e);
        }
    }

    public String getExcelColName() {
        int i = ordinal();
        int n = i / EXCEL_COLUMNS.length;
        int r = i % EXCEL_COLUMNS.length;
        return ordinal() + " / " + (n > 0 ? EXCEL_COLUMNS[n - 1] : "") + EXCEL_COLUMNS[r];

    }

}
