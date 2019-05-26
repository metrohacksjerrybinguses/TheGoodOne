package com.binguses.jerry.tools;

import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CSVTools {
    String csv;
    CSVWriter writer;
    CSVReader reader = null;
    ArrayList<Food> diet;
    static CSVTools tools = null;
    private CSVTools() {
        this.diet = new ArrayList<Food>();
        csv = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy");
        String dat = formatter.format(today);
        csv+="/"+dat+".csv";
    }

    public static CSVTools getInstance() {
        if (tools == null)
            tools = new CSVTools();
        return tools;
    }

    public void add(Food food) {
        diet.add(food);
    }

    public void remove(Food food) {
        diet.remove(food);
    }

    public void clear() {
        diet.clear();
        try {
            writer = new CSVWriter(new FileWriter(csv, false));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Food> getDiet() {
        return diet;
    }
    
    public double getTotal(){
        ArrayList<Food> a = getDiet();
        double sum = 0;
        for(Food f: a)
            sum+= f.getCalories();
        return sum;
    }

    public void setDiet(ArrayList<Food> diet) {
        this.diet = diet;
    }

    public void writeDiet() {
        try {
            writer = new CSVWriter(new FileWriter(csv, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> dietString = new ArrayList<String[]>();
        for (Food food : diet) {

            dietString.add(new String[]{food.name, Double.toString(food.calories)});
            Log.wtf("name",food.getName()+" "+food.calories);
        }
        writer.writeAll(dietString);
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                diet.add(new Food(food[0], Double.parseDouble(food[1]),food[2]));
            setDiet(diet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
