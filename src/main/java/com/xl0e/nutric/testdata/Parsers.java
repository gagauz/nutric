package com.xl0e.nutric.testdata;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class Parsers {

    private static final ThreadLocal<DecimalFormat> FMT_LOCAL = ThreadLocal.withInitial(
            () -> new DecimalFormat("0.#", DecimalFormatSymbols.getInstance(Locale.getDefault())));

    public static Parser<String> STRING = new Parser<String>() {
        @Override
        public String parse(String string) {
            return string;
        };

        @Override
        public String toString() {
            return "String";
        };
    };

    public static Parser<File> FILE = new Parser<File>() {
        @Override
        public File parse(String string) {
            return null == string ? null : new File(string);
        };

        @Override
        public String toString() {
            return "File";
        };
    };

    public static Parser<Path> PATH = new Parser<Path>() {
        @Override
        public Path parse(String string) {
            return null == string ? null : Paths.get(string);
        };

        @Override
        public String toString() {
            return "File";
        };
    };

    public static Parser<Integer> INTEGER = new Parser<Integer>() {
        @Override
        public Integer parse(String string) {
            return Integer.parseInt(string);
        };

        @Override
        public String toString() {
            return "Integer";
        };
    };

    public static Parser<BigDecimal> DECIMAL = new Parser<BigDecimal>() {
        @Override
        public BigDecimal parse(String string) {
            return new BigDecimal(string);
        };

        @Override
        public String toString() {
            return "Decimal";
        };
    };

    public static Parser<Float> FLOAT = new Parser<Float>() {
        @Override
        public Float parse(String string) {
            if (StringUtils.isEmpty(string)) {
                return 0.0f;
            }
            try {
                return FMT_LOCAL.get().parse(string).floatValue();
            } catch (ParseException e) {
                throw new IllegalStateException(e);
            }
        };

        @Override
        public String toString() {
            return "Float";
        };
    };

    public static Parser<Boolean> BOOL = new Parser<Boolean>() {
        @Override
        public Boolean parse(String string) {
            return new Boolean(string);
        };

        @Override
        public String toString() {
            return "Boolean";
        };
    };

    public static <E extends Enum<E>> Parser<E> enumParser(Class<E> enumClass) {
        return new EnumParser<>(enumClass);
    }

    public static class EnumParser<E extends Enum<E>> implements Parser<E> {

        public static <E extends Enum<E>> EnumParser<E> forClass(Class<E> clazz) {
            return new EnumParser<>(clazz);
        }

        private final Class<E> enumClass;

        EnumParser(Class<E> enumClass) {
            this.enumClass = enumClass;
        }

        @Override
        public E parse(String string) {
            for (E e : enumClass.getEnumConstants()) {
                if (e.name().equalsIgnoreCase(string)) {
                    return e;
                }
            }
            return null;
        };
    };

}
