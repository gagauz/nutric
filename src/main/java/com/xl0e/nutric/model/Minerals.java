package com.xl0e.nutric.model;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Minerals {
    private float fe;
    private float ca;
    private float mn;
    private float mg;
    private float mo;
    private float zn;
    private float se;
    private float na;
    private float k;
    private float j;
    private float f;
    private float cr;
    private float p;
    private float cu;
    private float si;

    public float getFe() {
        return fe;
    }

    public void setFe(float fe) {
        this.fe = fe;
    }

    public float getCa() {
        return ca;
    }

    public void setCa(float ca) {
        this.ca = ca;
    }

    public float getMn() {
        return mn;
    }

    public void setMn(float mn) {
        this.mn = mn;
    }

    public float getMg() {
        return mg;
    }

    public void setMg(float mg) {
        this.mg = mg;
    }

    public float getMo() {
        return mo;
    }

    public void setMo(float mo) {
        this.mo = mo;
    }

    public float getZn() {
        return zn;
    }

    public void setZn(float zn) {
        this.zn = zn;
    }

    public float getSe() {
        return se;
    }

    public void setSe(float se) {
        this.se = se;
    }

    public float getNa() {
        return na;
    }

    public void setNa(float na) {
        this.na = na;
    }

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public float getJ() {
        return j;
    }

    public void setJ(float j) {
        this.j = j;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public float getCr() {
        return cr;
    }

    public void setCr(float cr) {
        this.cr = cr;
    }

    public float getP() {
        return p;
    }

    public void setP(float p) {
        this.p = p;
    }

    public float getCu() {
        return cu;
    }

    public void setCu(float cu) {
        this.cu = cu;
    }

    public float getSi() {
        return si;
    }

    public void setSi(float si) {
        this.si = si;
    }

    @Transient
    public void add(Minerals minerals, float ratio) {
        ca += minerals.ca * ratio;
        cr += minerals.cr * ratio;
        cu += minerals.cu * ratio;
        f += minerals.f * ratio;
        fe += minerals.fe * ratio;
        j += minerals.j * ratio;
        k += minerals.k * ratio;
        mg += minerals.mg * ratio;
        mn += minerals.mn * ratio;
        mo += minerals.mo * ratio;
        na += minerals.na * ratio;
        p += minerals.p * ratio;
        se += minerals.se * ratio;
        si += minerals.si * ratio;
        zn += minerals.zn * ratio;
    }

    @Transient
    public void percent(Minerals minerals) {
        ca = ca / minerals.ca * 100;
        cr = cr / minerals.cr * 100;
        cu = cu / minerals.cu * 100;
        f = f / minerals.f * 100;
        fe = fe / minerals.fe * 100;
        j = j / minerals.j * 100;
        k = k / minerals.k * 100;
        mg = mg / minerals.mg * 100;
        mn = mn / minerals.mn * 100;
        mo = mo / minerals.mo * 100;
        na = na / minerals.na * 100;
        p = p / minerals.p * 100;
        se = se / minerals.se * 100;
        si = si / minerals.si * 100;
        zn = zn / minerals.zn * 100;

    }
}
