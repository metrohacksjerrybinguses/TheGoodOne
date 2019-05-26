package com.binguses.jerry.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

import com.binguses.jerry.tools.CSVTools;
import com.binguses.jerry.tools.Food;

import org.tensorflow.lite.examples.classification.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyfood);

        TextView total = (TextView) findViewById(R.id.totalcals);
        total.setText("Total Calories: "+ CSVTools.getInstance().getTotal());
        TextView date = (TextView) findViewById(R.id.date);
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dat = formatter.format(today);
        date.setText(dat);
        CSVTools.getInstance().readDiet();
        addNames();
        addCalories();
        addTimes();
    }

    public void addNames(){
        ArrayList<Food> a = CSVTools.getInstance().getDiet();
        TextView item = (TextView) findViewById(R.id.items);
        String names = "";
        for(Food f: a) {
            names += f.getName() +  "\n";
        }
        item.setText(names);
    }

    public void addCalories(){
        ArrayList<Food> a = CSVTools.getInstance().getDiet();
        TextView cal = (TextView) findViewById(R.id.caloriecounts);
        String names = "";
        for(Food f:a) {
            names += f.getCalories() + "\n";
        }
        cal.setText(names);
    }

    public void addTimes(){
        ArrayList<Food> a = CSVTools.getInstance().getDiet();
        TextView tim = (TextView) findViewById(R.id.times);
        String times = "";
        for (Food f : a) {
            times += f.getTime() + "\n";
        }
        tim.setText(times);
    }

    public void goToHome(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void clearFood(View v){
        CSVTools.getInstance().clear();
        TextView items = (TextView) findViewById(R.id.items);
        items.setText("");
        TextView cals = (TextView) findViewById(R.id.caloriecounts);
        cals.setText("");
        TextView totalCals = (TextView) findViewById(R.id.totalcals);
        totalCals.setText("");
        totalCals.setText("");
    }



}
