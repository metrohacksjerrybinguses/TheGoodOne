package com.binguses.jerry.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.binguses.jerry.tools.CSVTools;
import com.binguses.jerry.tools.Food;

import org.tensorflow.lite.examples.classification.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calories extends AppCompatActivity {

    double cal;
    String name;
    double serving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        TextView textView = (TextView) findViewById(R.id.foodName);
        TextView calories = (TextView) findViewById(R.id.caloriesNum);
        TextView num = (TextView) findViewById(R.id.servingss);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cal = (double) bundle.get("objCal");
        name = bundle.getString("objName");
        serving = 1;
        textView.setText(name);
        calories.setText(new Double(cal).toString());
        num.setText(Double.toString(serving));
    }

    public void goToDay(View v){
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String time = formatter.format(today);
        CSVTools.getInstance().add(new Food(name, cal * serving, time));
        CSVTools.getInstance().writeDiet();
        Intent intent = new Intent(this, DailyFood.class);
        startActivity(intent);
    }

    public void goToHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void up(View v) {
        if (serving>=1) serving++;
        else serving*=2;
        TextView num = (TextView) findViewById(R.id.servingss);
        num.setText(Double.toString(serving));
    }

    public void down(View v) {
        if (serving<=1) serving/=2;
        else serving--;
        TextView num = (TextView) findViewById(R.id.servingss);
        num.setText(Double.toString(serving));
    }
}
