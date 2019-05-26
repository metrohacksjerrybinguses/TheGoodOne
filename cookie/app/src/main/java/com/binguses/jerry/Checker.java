package com.binguses.jerry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.tensorflow.lite.examples.classification.R;

import java.util.ArrayList;
import java.util.List;

public class Checker extends AppCompatActivity {

    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);

        TextView textView = (TextView) findViewById(R.id.result);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        list = bundle.getStringArrayList("list");
        textView.setText(list.get(0));
    }

    public void goToCalories(View v) {
        Log.d("ajajajj","jjjaaa");
        Intent intent = new Intent(this, Calories.class);
        intent.putExtra("objName", list.get(0));
        startActivity(intent);
    }

    public void goToOptions(View v) {
        Intent intent = new Intent(this, Options.class);
        intent.putStringArrayListExtra("list", list);
        startActivity(intent);
    }
}
