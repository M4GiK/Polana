package com.m4gik.interfaces;

/**
 * Created by m4gik on 6/23/17.
 */
public interface Field {

    void setFieldArea(double squareMeters) throws CloneNotSupportedException;

    String growth(double percentage);

    void setActualGreenAcreage(double acreage);

    double getActualGreenAcreage();

    double getFieldArea();
}
