package com.example.user.keepsolid.ui.fragments.calendar;

/**
 * Created by root on 1/10/18.
 */

public class ModelCalendar {
    int id;
    String name;
    int year_start;
    int month_start;
    int day_start;
    int hours_start;
    int minut_start;
    int year_end;
    int month_end;
    int day_end;
    int hours_end;
    int minut_end;
    String color;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear_start() {
        return year_start;
    }

    public int getMonth_start() {
        return month_start;
    }

    public int getDay_start() {
        return day_start;
    }

    public int getHours_start() {
        return hours_start;
    }

    public int getMinut_start() {
        return minut_start;
    }

    public int getYear_end() {
        return year_end;
    }

    public int getMonth_end() {
        return month_end;
    }

    public int getDay_end() {
        return day_end;
    }

    public int getHours_end() {
        return hours_end;
    }

    public int getMinut_end() {
        return minut_end;
    }

    public String getColor() {
        return color;
    }

    public ModelCalendar(int id, String name, int year_start, int month_start, int day_start, int hours_start, int minut_start, int year_end, int month_end, int day_end, int hours_end, int minut_end, String color) {
        this.id = id;
        this.name = name;
        this.year_start = year_start;
        this.month_start = month_start;
        this.day_start = day_start;
        this.hours_start = hours_start;
        this.minut_start = minut_start;
        this.year_end = year_end;
        this.month_end = month_end;
        this.day_end = day_end;
        this.hours_end = hours_end;
        this.minut_end = minut_end;
        this.color = color;
    }
}
