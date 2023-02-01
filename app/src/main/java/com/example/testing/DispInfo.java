package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DispInfo extends AppCompatActivity {

    TextView name;
    TextView age;
    TextView dob;
    TextView medCond;
    TextView con1;
    TextView con2;
    TextView con3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_info);
        name = (TextView) findViewById(R.id.et_name);
        age = (TextView) findViewById(R.id.age);
        dob = (TextView) findViewById(R.id.dob);
        medCond = (TextView) findViewById(R.id.medCond);
        con1 = (TextView) findViewById(R.id.con1);
        con2 = (TextView) findViewById(R.id.con2);
        con3 = (TextView) findViewById(R.id.con3);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String value = extras.getString("key").replace("SMSTO::","");
            String[] data = value.split("\\|");
            Toast.makeText(this, data[0] + "", Toast.LENGTH_LONG).show();
            name.setText(data[0]);
            age.setText("Sex: " + data[1]);
            dob.setText("DOB: " + data[2]);
            medCond.setText("Prior Medical Conditions " + data[3]);
            con1.setText(data[4] + ": " + data[5]);
            con2.setText(data[6] + ": " + data[7]);
            con3.setText(data[8] + ": " + data[9]);
        }

    }
}