package com.binguses.jerry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.tensorflow.lite.examples.classification.R;

public class Calories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        TextView textView = (TextView) findViewById(R.id.foodName);
        TextView calories = (TextView) findViewById(R.id.calories);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
//        int cal = (Integer)bundle.get("objCal");
        String obj = bundle.getString("objName");
        textView.setText(obj);
//        calories.setText(cal);
    }

    public void goToDay(View v){
        Intent intent = new Intent(this, DailyFood.class);
        startActivity(intent);
    }

    public void goToHome(View v){
        Intent intent = new Intent(this, DailyFood.class);
        startActivity(intent);
    }
}
