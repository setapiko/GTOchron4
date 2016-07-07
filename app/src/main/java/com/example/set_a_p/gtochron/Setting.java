package com.example.set_a_p.gtochron;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final EditText etText = (EditText) findViewById(R.id.editText);
        final EditText etText2 = (EditText) findViewById(R.id.editText2);

        Button btnSave = (Button) findViewById(R.id.button3);

        View.OnClickListener oclBtnSave = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((etText!=null) && (etText2!=null)){
                SharedPreferences sPref = getSharedPreferences("User", MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("NAME", etText.getText().toString());
                ed.putString("FAMILY", etText2.getText().toString());
                ed.apply();}
            }
        };

        assert btnSave != null;
        btnSave.setOnClickListener(oclBtnSave);
    }
}
