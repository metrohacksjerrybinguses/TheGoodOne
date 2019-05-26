package com.binguses.jerry.tools;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVTools {
    String csv;
    CSVWriter writer;
    CSVReader reader = null;
    ArrayList<Food> diet;
    public CSVTools(ArrayList<Food> diet) {
        this.diet = diet;
        csv = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(Food food) {
        diet.add(food);
    }

    public void remove(Food food) {
        diet.remove(food);
    }

    public void clear() {
        diet.clear();
    }

    public ArrayList<Food> getDiet {
        return diet;
    }

    public void writeDiet() {
        ArrayList<String[]> dietString = new ArrayList<String[]>();
        for (Food food : diet)
            dietString.add(new String[]{food.name, Double.toString(food.calories)});
        writer.writeAll(dietString);
    }

    public void readDiet() {
        if (reader == null) {
            try {
                reader = new CSVReader(new FileReader(csv));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            List<String[]> dietString = reader.readAll();
            ArrayList<Food> diet = new ArrayList<Food>();
            for (String[] food : dietString)
                diet.add(new Food(food[0], Double.parseDouble(food[1])));
            setDiet(diet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
