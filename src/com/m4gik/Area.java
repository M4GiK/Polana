package com.m4gik;

/**
 * Created by m4gik on 6/23/17.
 */
public class Area implements Cloneable {

    private double acreage;

    Area(double acreage) {
        setAcreage(acreage);
    }

    public Area clone() throws CloneNotSupportedException {
        return (Area)super.clone();
    }

    double getAcreage() {
        return acreage;
    }

    void setAcreage(double acreage) {
        this.acreage = acreage;
    }
}
