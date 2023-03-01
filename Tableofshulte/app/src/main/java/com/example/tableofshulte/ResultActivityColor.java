package com.example.tableofshulte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class ResultActivityColor extends AppCompatActivity {

    private TextView textView;

    private String time;
    private Intent intent;
    private String access = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_color);

        textView = findViewById(R.id.textView4);

        time = getIntent().getStringExtra("secondsColor");
        textView.setText("молодец ты справился за " + time);
    }

    public void toRecords(View view) {
        intent = new Intent(this, Records.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("seconds", getIntent().getStringExtra("seconds"));
        intent.putExtra("secondsColor", time);
        intent.putExtra("access", access);
        startActivity(intent);
    }
}