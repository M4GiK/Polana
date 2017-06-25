package com.m4gik.models;

import com.m4gik.interfaces.Field;

/**
 * Created by m4gik on 6/23/17.
 */
public class Grassland implements Field {

    private Area squareMeters;
    private Area greenAcreageSquareMaters;

    private static Grassland instance = null;
    protected Grassland(){}
    public static Grassland getInstance() {
        if(instance == null) {
            instance = new Grassland();
        }
        return instance;
    }

    @Override
    public void setFieldArea(double squareMeters) throws CloneNotSupportedException {
        this.squareMeters = new Area(squareMeters);
        this.greenAcreageSquareMaters = this.squareMeters.clone();
    }

    @Override
    public void growth(double percentage) {
        setActualGreenAcreage(getActualGreenAcreage() +  (this.squareMeters.getAcreage() - getActualGreenAcreage()) * percentage);
    }

    @Override
    public void setActualGreenAcreage(double acreage) {
        this.greenAcreageSquareMaters.setAcreage(acreage);
    }

    @Override
    public double getActualGreenAcreage() {
        return this.greenAcreageSquareMaters.getAcreage();
    }

    @Override
    public double getFieldArea() {
        return this.squareMeters.getAcreage();
    }
}
