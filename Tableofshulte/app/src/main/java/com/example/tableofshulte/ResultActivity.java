package com.example.tableofshulte;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DirectAction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private TextView textResult;
    int totalSeconds;

    private Button buttonBack;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textResult = findViewById(R.id.textResult);
        textResult.setText(R.string.txt_result);
        buttonBack = findViewById(R.id.buttonBack);


        totalSeconds = getIntent().getIntExtra("seconds", 0);
        int hours = totalSeconds / 3600 ;
        int minutes = (totalSeconds % 3600 ) / 60 ;
        int secs = totalSeconds % 60 ;
        time = String.format(Locale.getDefault(),
                "%d:%02d:%02d" , hours, minutes, secs);

        textResult.setText("ты справился за: " + time);
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivityColor.class);
        String name = getIntent().getStringExtra("name");
        intent.putExtra("name", name);
        intent.putExtra("seconds", time);
        startActivity(intent);
    }
}