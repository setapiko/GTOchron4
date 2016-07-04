package com.example.set_a_p.gtochron;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class GTOchron extends AppCompatActivity {

    TextView tvOut;
    Button btnOk;
    Button btnCancel;
    Chronometer chron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gtochron);


        tvOut = (TextView) findViewById(R.id.textView);
        btnOk = (Button) findViewById(R.id.button);
        btnCancel = (Button) findViewById(R.id.button2);
        chron=(Chronometer) findViewById(R.id.chronometer);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chron.setBase(SystemClock.elapsedRealtime());
                tvOut.setText("Золото");
                chron.start();

            }
        };

        chron.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime()
                        - chron.getBase();

                if (elapsedMillis > 5000) {
                    tvOut.setText("Серебро");
                }

                if (elapsedMillis > 7000) {
                    tvOut.setText("Бронза");
                }
                if (elapsedMillis > 9999) {
                    tvOut.setText("Незачет");
                }
            }});


        btnOk.setOnClickListener(oclBtnOk);
    }
}
