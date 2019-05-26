package com.binguses.jerry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
public class Scraper {


    public double crawl(String s){
        double calories = 0;
        String food = s;
        String url = "http://www.acaloriecounter.com/search/" + food.replace(' ','_');
        Elements search = null;
        try {
            search = Jsoup.connect(url).get().select("a[href]");
        } catch (IOException e) {
            e.printStackTrace();
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

        return calories;
    }

}