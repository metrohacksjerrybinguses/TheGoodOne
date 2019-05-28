package com.binguses.jerry.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
        while(list.size()<5)
            list.add("");
        TextView tv1 = (TextView) findViewById(R.id.option1);
        TextView tv2 = (TextView) findViewById(R.id.option2);
        TextView tv3 = (TextView) findViewById(R.id.option3);
        TextView tv4 = (TextView) findViewById(R.id.option4);
        TextView tv5 = (TextView) findViewById(R.id.option5);
        tv1.setText(list.get(1));
        tv2.setText(list.get(2));
        tv3.setText(list.get(3));
        tv4.setText(list.get(4));
        tv5.setText(list.get(5));
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
        intent.putExtra("objName",list.get(5));
        startActivity(intent);
    }

}
