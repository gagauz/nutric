package com.xl0e.nutric.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Vitamins {
    private float a;
    private float b1;
    private float b2;
    private float b3;
    private float b5;
    private float b6;
    private float b7;
    private float b9;
    private float b12;
    private float c;
    private float d;
    private float e;
    private float k;
    private float pp;

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB1() {
        return b1;
    }

    public void setB1(float b1) {
        this.b1 = b1;
    }

    public float getB2() {
        return b2;
    }

    public void setB2(float b2) {
        this.b2 = b2;
    }

    public float getB3() {
        return b3;
    }

    public void setB3(float b3) {
        this.b3 = b3;
    }

    public float getB5() {
        return b5;
    }

    public void setB5(float b5) {
        this.b5 = b5;
    }

    public float getB6() {
        return b6;
    }

    public void setB6(float b6) {
        this.b6 = b6;
    }

    public float getB7() {
        return b7;
    }

    public void setB7(float b7) {
        this.b7 = b7;
    }

    public float getB9() {
        return b9;
    }

    public void setB9(float b9) {
        this.b9 = b9;
    }

    public float getB12() {
        return b12;
    }

    public void setB12(float b12) {
        this.b12 = b12;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public float getD() {
        return d;
    }

    public void setD(float d) {
        this.d = d;
    }

    public float getE() {
        return e;
    }

    public void setE(float e) {
        this.e = e;
    }

    @Column(name = "kalium")
    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public float getPp() {
        return pp;
    }

    public void setPp(float pp) {
        this.pp = pp;
    }

    public void add(Vitamins vitamins, float ratio) {
        a += vitamins.a * ratio;
        b1 += vitamins.b1 * ratio;
        b12 += vitamins.b12 * ratio;
        b2 += vitamins.b2 * ratio;
        b3 += vitamins.b3 * ratio;
        b5 += vitamins.b5 * ratio;
        b6 += vitamins.b6 * ratio;
        b7 += vitamins.b7 * ratio;
        b9 += vitamins.b9 * ratio;
        c += vitamins.c * ratio;
        d += vitamins.d * ratio;
        e += vitamins.e * ratio;
        k += vitamins.k * ratio;
        pp += vitamins.pp * ratio;
    }

    @Transient
    public void percent(Vitamins vitamins) {
        a = a / vitamins.a * 100;
        b1 = b1 / vitamins.b1 * 100;
        b12 = b12 / vitamins.b12 * 100;
        b2 = b2 / vitamins.b2 * 100;
        b3 = b3 / vitamins.b3 * 100;
        b5 = b5 / vitamins.b5 * 100;
        b6 = b6 / vitamins.b6 * 100;
        b7 = b7 / vitamins.b7 * 100;
        b9 = b9 / vitamins.b9 * 100;
        c = c / vitamins.c * 100;
        d = d / vitamins.d * 100;
        e = e / vitamins.e * 100;
        k = k / vitamins.k * 100;
        pp = pp / vitamins.pp * 100;
    }
}
