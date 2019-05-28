package com.binguses.jerry.tools;

public class Food {
    String name;
    double calories;
    String time;
    public Food(String name, double calories, String time) {
        this.name = name;
        this.calories = calories;
        this.time = time;
    }

    public double getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public String getTime() { return time; }
}
