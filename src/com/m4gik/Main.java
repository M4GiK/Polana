package com.m4gik;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private final static double ACREAGE = 30000.0;
    private final static double RABBIT_MEAL_SIZE = 0.25;
    private final static int RABBIT_AMOUNT = 100;
    private final static int RABBIT_DINNER_TIME = 19;
    private final static LocalDateTime START_DAY = LocalDateTime.of(2002, Month.MAY, 1, 0, 0);
    private final static LocalDateTime END_DAY = LocalDateTime.of(2002, Month.JUNE, 1, RABBIT_DINNER_TIME, 0);


    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("M4GiK Software - Polana program");
        Field field = initializeGrassland();
        Set<Rabbit> rabbits = initializeRabbits(field);

        exercise1a(rabbits, field, START_DAY, END_DAY);
    }

    /**
     * 1 a) Jaka część polany (w procentach) będzie zarośnięta 1 czerwca 2002 roku
     * tuż przed wieczornym posiłkiem królików? Wpisz odpowiedź (z dokładnością do 0,01%).
     *
     * @param rabbits
     * @param field
     * @param startDay
     * @param endDay
     */
    private static void exercise1a(Set<Rabbit> rabbits, Field field, LocalDateTime startDay, LocalDateTime endDay) {
        startGrasslandLife(rabbits, field, START_DAY, END_DAY);
        System.out.println("Porośnięcie polany przezd wieczornym posiłkiem dnia " + END_DAY + " wynosi: "
                + round((field.getActualGreenAcreage() * 100.0) / field.getFieldArea(), 2) + "%");
    }

    private static void startGrasslandLife(Set<Rabbit> rabbits, Field field, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int i=0;
        do {
            rabbitConsuming(rabbits);
            field.growth(0.05);
            startDateTime = startDateTime.plusDays(1);
            if ((int)Duration.between(startDateTime, endDateTime).toHours() > RABBIT_DINNER_TIME) {
                rabbitConsuming(rabbits);
            }
        } while (Duration.between(startDateTime, endDateTime).toDays() > 0);
    }

    private static void rabbitConsuming(Set<Rabbit> rabbits) {
        for (Rabbit rabbit : rabbits) {
            rabbit.eating();
        }
    }

    private static Set<Rabbit> initializeRabbits(Field field) {
        Set<Rabbit> Rabbits = new HashSet<>();
        for (int i = 0; i < RABBIT_AMOUNT; i++) {
            Rabbit rabbit = new Rabbit(field);
            rabbit.setFoodDemand(new Area(RABBIT_MEAL_SIZE));
            Rabbits.add(rabbit);
        }

        return Rabbits;
    }

    private static Grassland initializeGrassland() throws CloneNotSupportedException {
        Grassland field = Grassland.getInstance();
        field.setFieldArea(ACREAGE);

        return field;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
