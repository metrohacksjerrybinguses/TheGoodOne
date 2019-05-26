package com.binguses.jerry.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        TextView date = (TextView) findViewById(R.id.date);
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dat = formatter.format(today);
        date.setText(dat);
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





}
