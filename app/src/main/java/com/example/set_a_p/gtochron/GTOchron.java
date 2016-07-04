package com.example.set_a_p.gtochron;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class GTOchron extends AppCompatActivity {

    TextView tvOut;
    Button btnOk;
    Chronometer chron;

    int[] time = new int[3];
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gtochron);


        tvOut = (TextView) findViewById(R.id.textView);
        btnOk = (Button) findViewById(R.id.button);
        chron=(Chronometer) findViewById(R.id.chronometer);
        final RadioButton r1 = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton r2 = (RadioButton) findViewById(R.id.radioButton2);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id == 1) {
                    if (r1.isChecked()) {
                        time[0] = 13500;
                        time[1] = 14800;
                        time[2] = 15100;
                    } else {
                        time[0] = 16000;
                        time[1] = 16500;
                        time[2] = 17000;
                    }
                    btnOk.setText("Стоп");
                    chron.setBase(SystemClock.elapsedRealtime());
                    tvOut.setText("Золото");
                    chron.start();
                    id = 2;
                } else {
                    id = 1;
                    btnOk.setText("Старт");
                    chron.stop();
                }

            }
        };


        chron.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime()
                        - chron.getBase();


                if (elapsedMillis > time[0]) {
                    tvOut.setText("Серебро");
                }

                if (elapsedMillis > time[1]) {
                    tvOut.setText("Бронза");
                }
                if (elapsedMillis > time[2]) {
                    tvOut.setText("Незачет");
                }
            }});

        View.OnClickListener rad1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r2.isChecked()) {
                    r2.setChecked(false);
                }
            }
        };

        View.OnClickListener rad2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1.isChecked()) {
                    r1.setChecked(false);
                }
            }
        };

        r1.setOnClickListener(rad1);
        r2.setOnClickListener(rad2);
        btnOk.setOnClickListener(oclBtnOk);
    }
}
