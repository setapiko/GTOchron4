package com.example.set_a_p.gtochron;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class GTOchron extends AppCompatActivity {

    Intent intent;
    TextView tvOut;
    Button btnOk;
    Button btnAdd;
    Chronometer chron;

    private RealmConfiguration realmConfig;

    int[] time = new int[3];
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gtochron);


        tvOut = (TextView) findViewById(R.id.textView);
        btnOk = (Button) findViewById(R.id.button);
        btnAdd = (Button) findViewById(R.id.button2);
        chron=(Chronometer) findViewById(R.id.chronometer);
        final RadioButton r1 = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton r2 = (RadioButton) findViewById(R.id.radioButton2);
        realmConfig = new RealmConfiguration.Builder(this).build();

        btnAdd.setEnabled(false);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id == 1) {
                    assert r1 != null;
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
                    r1.setEnabled(false);
                    assert r2 != null;
                    r2.setEnabled(false);
                    btnAdd.setEnabled(false);
                    id = 2;
                } else {
                    id = 1;
                    btnOk.setText("Старт");
                    btnAdd.setEnabled(true);
                    assert r1 != null;
                    r1.setEnabled(true);
                    assert r2 != null;
                    r2.setEnabled(true);
                    chron.stop();
                }

            }
        };


        View.OnClickListener oclBtnAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realm = Realm.getInstance(realmConfig);

                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm bgRealm) {
                        TimeBase user = bgRealm.createObject(TimeBase.class);
                        SharedPreferences sPref = getSharedPreferences("User", MODE_PRIVATE);
                        if ((sPref.contains("NAME")) && (sPref.contains("FAMILY"))) {
                            user.setName(sPref.getString("NAME", ""));
                            user.setFamily(sPref.getString("FAMILY", ""));
                        }else {
                            user.setName("Unknown");
                            user.setFamily("Unknown");
                        }
                        assert r1 != null;
                        if (r1.isChecked())
                        user.setGender(true);
                        else user.setGender(false);
                        user.setTime(chron.getText().toString());
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        // Transaction was a success.
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        // Transaction failed and was automatically canceled.
                    }
                });

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
                assert r2 != null;
                if (r2.isChecked()) {
                    r2.setChecked(false);
                }
            }
        };

        View.OnClickListener rad2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert r1 != null;
                if (r1.isChecked()) {
                    r1.setChecked(false);
                }
            }
        };

        assert r1 != null;
        r1.setOnClickListener(rad1);
        assert r2 != null;
        r2.setOnClickListener(rad2);
        btnOk.setOnClickListener(oclBtnOk);
        btnAdd.setOnClickListener(oclBtnAdd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
            { intent = new Intent(GTOchron.this, Setting.class);
                startActivity(intent);
            return true;}
            case R.id.action_base:
            { intent = new Intent(GTOchron.this, base.class);
                startActivity(intent);
            return true;}
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
