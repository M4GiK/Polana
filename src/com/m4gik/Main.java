package com.m4gik;

import com.m4gik.interfaces.Field;
import com.m4gik.models.*;
import com.m4gik.models.table.Row;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private static final double ACREAGE = 30000.0;
    private static final double RABBIT_MEAL_SIZE = 0.25;
    private static final int RABBIT_AMOUNT = 100;
    private static final int RABBIT_DINNER_TIME = 19;
    private static final LocalDateTime START_DAY = LocalDateTime.of(2002, Month.MAY, 1, 0, 0);
    private static final LocalDateTime END_DAY = LocalDateTime.of(2002, Month.JUNE, 2, RABBIT_DINNER_TIME, 0);
    private static final String RABBIT_DATE_BREAKFAST_CONSUMING_HEADER = "Data porannego posiłku";
    private static final String RABBIT_DATE_DINNER_CONSUMING_HEADER = "Data wieczorengo posiłku";
    private static final String RABBIT_FIELD_BREAKFAST_CONSUMING_HEADER = "Wielkość zielonej polany po świcie";
    private static final String RABBIT_FIELD_DINNER_CONSUMING_HEADER = "Wielkość zielonej polany pod wieczór";
    private static final String FIELD_GROWTH_HEADER = "Wzrost polany";
    private static final String TABLE_FILE_NAME = "tabelka.txt";


    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        System.out.println("M4GiK Software - Polana program");
        Field field = initializeGrassland();
        Set<Rabbit> rabbits = initializeRabbits(field);

        exercise1a(rabbits, field, START_DAY, END_DAY);
        exercise1b(rabbits, field, START_DAY, END_DAY);
    }

    /**
     * Załóżmy, że prowadzisz obserwacje polany codziennie pod wieczór
     * (ale przed posiłkiem królików) — i tylko wtedy.
     * Utwórz tabelkę przedstawiającą zależność zarośniętej powierzchni
     * od czasu w okresie od 1 maja do 1 czerwca 2002 r.
     *
     * @param rabbits Sets of rabbits.
     * @param field Grassland for rabbits.
     * @param startDay Day, from which is started observation.
     * @param endDay Day, from which is ended observation.
     */
    private static void exercise1b(Set<Rabbit> rabbits, Field field, LocalDateTime startDay, LocalDateTime endDay) throws IOException {
        FileOperations fileOperations = new FileOperations(TABLE_FILE_NAME);
        fileOperations.saveFile(fileOperations.addFileContent(startGrasslandLife(rabbits, field, START_DAY, END_DAY)));
    }

    /**
     * 1 a) Jaka część polany (w procentach) będzie zarośnięta 1 czerwca 2002 roku
     * tuż przed wieczornym posiłkiem królików? Wpisz odpowiedź (z dokładnością do 0,01%).
     *
     * @param rabbits Sets of rabbits.
     * @param field Grassland for rabbits.
     * @param startDay Day, from which is started observation.
     * @param endDay Day, from which is ended observation.
     */
    private static void exercise1a(Set<Rabbit> rabbits, Field field, LocalDateTime startDay, LocalDateTime endDay) {
        startGrasslandLife(rabbits, field, START_DAY, END_DAY);
        System.out.println("Porośnięcie polany przezd wieczornym posiłkiem dnia " + END_DAY + " wynosi: "
                + round((field.getActualGreenAcreage() * 100.0) / field.getFieldArea(), 2) + "%");
    }

    private static List<DayInformation> startGrasslandLife(Set<Rabbit> rabbits, Field field, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<DayInformation> daysInformations = new ArrayList<>();

        do {
            DayInformation dayInformation = new DayInformation();
            dayInformation.addInformation(RABBIT_DATE_BREAKFAST_CONSUMING_HEADER, startDateTime);
            dayInformation.addInformation(RABBIT_FIELD_BREAKFAST_CONSUMING_HEADER, rabbitConsuming(rabbits, field));
            dayInformation.addInformation(FIELD_GROWTH_HEADER, field.growth(0.05));
            dayInformation.addInformation(RABBIT_DATE_DINNER_CONSUMING_HEADER, startDateTime.plusHours(RABBIT_DINNER_TIME));
            startDateTime = startDateTime.plusDays(1);
            if ((int)Duration.between(startDateTime, endDateTime).toHours() > RABBIT_DINNER_TIME) {
                dayInformation.addInformation(RABBIT_FIELD_DINNER_CONSUMING_HEADER, rabbitConsuming(rabbits, field));
            }
            daysInformations.add(dayInformation);
        } while (Duration.between(startDateTime, endDateTime).toDays() > 0);

        return daysInformations;
    }

    private static Row rabbitConsuming(Set<Rabbit> rabbits, Field field) {
        for (Rabbit rabbit : rabbits) {
            rabbit.eating();
        }

        return new Row(round((field.getActualGreenAcreage() * 100.0) / field.getFieldArea(), 2) + "%");
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
