package com.binguses.jerry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tensorflow.lite.examples.classification.R;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    private Button getBtn;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        result = (TextView) findViewById(R.id.result);
        getBtn = (Button) findViewById(R.id.getBtn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite();
            }
        });

    }

    private void getWebsite(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("https://www.ssaurel.com/blog").get();
                    String title = doc.title();
                    Elements links = doc.select("a[href]");

                    builder.append(title).append("\n");

                    for (Element link : links){
                        builder.append("\n").append("Link: ").append(link.attr("href")).append("\n").append("Text: ").append(link.text());

                    }

                } catch (IOException e) {
                    builder.append("Error: ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }


        }).start();




    }



}
