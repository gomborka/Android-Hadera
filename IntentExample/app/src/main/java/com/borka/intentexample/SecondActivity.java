package com.borka.intentexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String SECOND_NAME= " tra-tra-tra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

       String name = getIntent().getStringExtra(MainActivity.NAME);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(name);
    }

    public void OnClickSendBack(View view) {
        EditText editText = (EditText)findViewById(R.id.editText);
        String secondName= editText.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra(SECOND_NAME,secondName);
        setResult(200,intent);

        finish();
    }


}
