package com.example.tableofshulte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private EditText editTextStart;
    private TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        editTextStart = findViewById(R.id.editTextTextPersonName);
        textViewInfo = findViewById(R.id.textViewInfo);
    }

    public void start(View view) {
        if (editTextStart.length() <= 2) {
            textViewInfo.setText("Введено неккоректное имя");
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            red.access = "1";
            String name = editTextStart.getText().toString();
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }

    public void records(View view) {
        Intent intent = new Intent(this, Records.class);
        startActivity(intent);
    }
}