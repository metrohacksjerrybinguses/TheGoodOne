package com.binguses.jerry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.tensorflow.lite.examples.classification.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Checker extends AppCompatActivity {

    Scraper scraper = new Scraper();

    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);

        TextView textView = (TextView) findViewById(R.id.result);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        list = bundle.getStringArrayList("list");
        try {
            textView.setText(list.get(0));
        } catch (java.lang.IndexOutOfBoundsException e) {
            Toast.makeText(this, "No food found, please take another picture", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(this,MainActivity.class);
            startActivity(intent1);
        }
    }

    public void goToCalories(View v) {
        Intent intent = new Intent(this, Calories.class);
        intent.putExtra("objName", list.get(0));
        scraper.setFood(list.get(0));
        try {
            scraper.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double cal = scraper.getCalories();
        intent.putExtra("objCal",cal);
        startActivity(intent);
    }

    public void goToOptions(View v) {
        Intent intent = new Intent(this, Options.class);
        intent.putStringArrayListExtra("list", list);
        startActivity(intent);
    }
}
