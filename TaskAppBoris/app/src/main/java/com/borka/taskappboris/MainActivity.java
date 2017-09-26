package com.borka.taskappboris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUser;
    private EditText etPassword;
    private Button btRegister;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser=(EditText)findViewById(R.id.editTextUser);
        etPassword=(EditText)findViewById(R.id.editTextPassword);
        btRegister = (Button)findViewById(R.id.buttonLogin);
        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                if (etUser.getText().length() > 0 && etPassword.getText().length() > 0) {


                    editor.putString("user", etUser.getText().toString());
                    editor.putString("password", etPassword.getText().toString());

                    editor.commit();
                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                } else {
                    Toast.makeText(MainActivity.this, "Fill empty field", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
