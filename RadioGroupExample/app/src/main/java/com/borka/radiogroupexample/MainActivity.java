package com.borka.radiogroupexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton red,blue,cyan,green;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        red=(RadioButton)findViewById(R.id.radioButton1);
        blue=(RadioButton)findViewById(R.id.radioButton2);
        cyan=(RadioButton)findViewById(R.id.radioButton3);
        green=(RadioButton)findViewById(R.id.radioButton4);






    }
}
