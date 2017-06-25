package com.m4gik;

/**
 * Created by m4gik on 6/23/17.
 */

/**
 * Na polanie żyje pewna ilość królików, które żywią się trawą.
 * Króliki żerują dwa razy dziennie: o świcie oraz o zmierzchu.
 * Podczas każdego posiłku jeden królik zjada trawę z ćwierci metra
 * kwadratowego polany.
 */
public class Rabbit implements Animal {

    private String animalName = "Rabbit";
    private Field field = null;
    private Area foodDemand = null;


    Rabbit(Field field) {
        this.field = field;
    }

    @Override
    public String getName() {
        return animalName;
    }

    @Override
    public void setName(String name) {
        this.animalName = name;
    }

    @Override
    public void eating() {
        field.setActualGreenAcreage(field.getActualGreenAcreage() - getFoodDemand().getAcreage());
    }

    @Override
    public void setFoodDemand(Area acreage) {
        this.foodDemand = acreage;
    }

    @Override
    public Area getFoodDemand() {
        return this.foodDemand;
    }
}
