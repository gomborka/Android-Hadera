package com.borka.intentexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
public static String NAME="bla-bla";
public static int CODE_TO_SECOND=100;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    tv = (TextView)findViewById(R.id.textView);

    }

    public void myClickMethod(View view) {
        EditText editText = (EditText)findViewById(R.id.editText);
        String name = editText.getText().toString().trim();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(NAME,name);
        //startActivity(intent);
        startActivityForResult(intent,CODE_TO_SECOND);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == CODE_TO_SECOND) {

            if (resultCode==200) {
                Toast.makeText(this, "we are back", Toast.LENGTH_SHORT).show();
                tv.setText(intent.getStringExtra(SecondActivity.SECOND_NAME));
            }
        }
    }

}
