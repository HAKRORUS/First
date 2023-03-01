package com.example.tableofshulte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button[] btns; //массив кнопок
    private TableLayout tableNums; //поле для кнопок
    private ArrayList<Integer> listNums = new ArrayList<>(); //список чисел для заполнения

    private int grade = 2; //размерность таблицы
    private int countClick = 1; //счетчик нажатия нужного числа
    private TextView textHelp; //подсказка

    private TextView timeInfo;
    private int seconds = 0;
    private boolean running = true;

    private String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = getIntent().getStringExtra("name");
        tableNums = findViewById(R.id.tableNums);
        tableNums.setShrinkAllColumns(true);
        tableNums.setGravity(Gravity.CENTER);

        timeInfo = findViewById(R.id.timeInfo);
        textHelp = findViewById(R.id.textHelp);

               runTimer();

        startApp(); //метод построения поля чисел и запуска остальной части

    }

    private void startApp() {
        if (grade <= 6) {
            btns = new Button[grade*grade];
            for (int i = 0; i < (grade * grade); i++) {
                listNums.add(i+1);
            }
            Collections.shuffle(listNums);
            int count = 0;
            for (int i = 0; i < grade; i++) {
                TableRow tableRow = new TableRow(this);
                tableRow.setGravity(Gravity.CENTER);
                for (int j = 0; j < grade; j++) {
                    btns[count] = new Button(this);
                    btns[count].setOnClickListener(this::click);
                    btns[count].setClickable(false);
                    btns[count].setText(String.valueOf(listNums.get(count)));
                    btns[count].setTextSize(50.0f - grade*4.1f);
                    tableRow.addView(btns[count]);
                    count++;
                }
                tableNums.addView(tableRow);
            }

            game(); //запуск игры (разблокировка первой кнопки)


        } else { //окончание игры - переход на экран с результатом
            running = false;
            textHelp.setText("");
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("name",name);
            intent.putExtra("seconds", seconds);
            startActivity(intent);
        }
    }

    private void click(View view) {
        //проверка окончания уровня (только что нажата последняя кнопка)
        if (countClick == grade*grade) {
            grade++;
            tableNums.removeAllViews(); //очищаем поле
            listNums.clear(); //чистим список
            countClick = 1; //сбрасываем счетчик

            startApp(); //запускаем заново игру
        } else {
            //иначе блокируем только что нажатую кнопку
            Button button = (Button) view;
            button.setClickable(false);
//            button.setBackgroundColor(Color.GREEN); //упрощает проверки
            countClick++; //плюсуем счетчик
            game(); //и переходим в гейм, чтобы разблокировать следующую
        }
}

    private void game() {
        for (Button btn : btns) {
            //ищем соответствие текста кнопки счетчику
            if (Integer.parseInt(btn.getText().toString()) == countClick) {
                btn.setClickable(true); //разблокируем и ждем нажатия
                textHelp.setText(R.string.txt_help);
                textHelp.append(" " + countClick);
            }
        }
    }

    private void runTimer() {
        final Handler handler = new Handler(); //используется встроенный класс
        //у него используем метод post(), который в отдельном потоке выполняет код (как я понял)
        handler.post(new Runnable() { //Runnable() - конструктор интерфейса,
            // поэтому переопределяем метод run()
            @Override
            public void run() { //этот метод будет выполняться каждую секунду благодаря задержке
                int hours = seconds / 3600 ;
                int minutes = (seconds % 3600 ) / 60 ;
                int secs = seconds % 60 ;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d" , hours, minutes, secs);
                timeInfo.setText(time);
                if (running) seconds++; //если running = фолс, метод выполняется,
                // просто расчет не меняется, так как секунды не увеличиваются
                handler.postDelayed(this, 1000); //метод задержки
            }
        });
    }
}