package com.example.tableofshulte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Records extends AppCompatActivity {
    private TableLayout tableOfRecords;
    private TextView textView;

    private String name;
    private String seconds;
    private String secondsColor;

    private ArrayList<TextView> textViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        tableOfRecords = findViewById(R.id.TableOfRecords);

        name = getIntent().getStringExtra("name");
        seconds = getIntent().getStringExtra("seconds");
        secondsColor = getIntent().getStringExtra("secondsColor");

        textView = findViewById(R.id.textView2);
        if (red.access.equals("1")) {
            textView.setText("*Первое время для 1 цвета, второе для 2 цветов*");
            if (name != null) {
                red.getArrayList().add(name + ": " + seconds + " " + secondsColor);
            }

            ArrayList<TextView> textViews = new ArrayList<>();

            for (int i = 0; i < red.getArrayList().size(); i++) {

                TableRow tableRow = new TableRow(this);
                tableRow.setGravity(Gravity.CENTER);

                TextView textView1 = new  TextView(this);

                textViews.add(i,textView1);
                textViews.get(i).setText(red.getArrayList().get(i));
                tableRow.addView(textViews.get(i));
                tableOfRecords.addView(tableRow);
                textView1.setTextSize(30);
            }
        } else {
            textView.setText("Пока что тут ничего нет, будьте первыми)))");
        }
    }

    public void menu(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}