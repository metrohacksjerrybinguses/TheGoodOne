package com.binguses.jerry.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.binguses.jerry.gui.Calories;

import org.tensorflow.lite.examples.classification.R;

import java.util.ArrayList;

public class Options extends AppCompatActivity {

    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        list = bundle.getStringArrayList("list");
    }

    public void opt1(View v) {
        Intent intent = new Intent(this, Calories.class);
        intent.putExtra("objName",list.get(1));
        startActivity(intent);
    }

    public void opt2(View v) {
        Intent intent = new Intent(this, Calories.class);
        intent.putExtra("objName",list.get(2));
        startActivity(intent);
    }

    public void opt3(View v) {
        Intent intent = new Intent(this, Calories.class);
        intent.putExtra("objName",list.get(3));
        startActivity(intent);
    }

    public void opt4(View v) {
        Intent intent = new Intent(this, Calories.class);
        intent.putExtra("objName",list.get(4));
        startActivity(intent);
    }

    public void opt5(View v) {
        Intent intent = new Intent(this, Calories.class);
        intent.putExtra("objName",list.get(1));
        startActivity(intent);
    }

}
