package com.m4gik;

/**
 * Created by m4gik on 6/23/17.
 */
public interface Animal {

    String getName();

    void setName(String name);

    void eating();

    void setFoodDemand(Area acreage);

    Area getFoodDemand();

}
