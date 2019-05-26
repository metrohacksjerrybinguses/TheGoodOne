package com.binguses.jerry.tools;

public class Food {
    String name;
    double calories;
    public Food(String name, double calories) {
        this.name = name;
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }
}
