package com.binguses.jerry.tools;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
public class Scraper extends AsyncTask<Double,Double, Double> {

    private String food;
    private double calories;
    public void setFood(String food) {
        this.food = food;
    }
    public double getCalories(){
        return calories;
    }


    @Override
    protected Double doInBackground(Double... voids) {
        double calories = -1;
        String url = "http://www.acaloriecounter.com/search/" + food.replace(' ','_');
        Elements search = null;
        try {
            search = Jsoup.connect(url).get().select("a[href]");
            Log.wtf("Food",food);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                search = Jsoup.connect(url).get().select("a[href]");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        String result = null;
        for (Element link : search) {
            if (link.attr("href").contains(food.replace(' ','-'))) {
                result = link.attr("href");
                break;
            }
        }
        if (result != null) {
            Document doc = null;
            try {
                doc = Jsoup.connect(result).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            calories = Double.parseDouble(doc.select("li.calories").text().split(" ")[1]);
            System.out.println(calories);
        } else
            System.out.println("No result found");


        this.calories = calories;
        return null;
    }

    @Override
    protected void onPostExecute(Double result) {

    }
}