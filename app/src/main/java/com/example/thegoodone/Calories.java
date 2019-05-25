package com.example.thegoodone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Calories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);
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
