package com.binguses.jerry.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.binguses.jerry.tools.CSVTools;
import com.binguses.jerry.tools.Food;

import org.tensorflow.lite.examples.classification.R;

public class Calories extends AppCompatActivity {

    double cal;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        TextView textView = (TextView) findViewById(R.id.foodName);
        TextView calories = (TextView) findViewById(R.id.caloriesNum);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cal = (double) bundle.get("objCal");
        name = bundle.getString("objName");
        textView.setText(name);
        calories.setText(new Double(cal).toString());
    }

    public void goToDay(View v){
        CSVTools.getInstance().add(new Food(name, cal));
        CSVTools.getInstance().writeDiet();
        Intent intent = new Intent(this, DailyFood.class);
        startActivity(intent);
    }

    public void goToHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
