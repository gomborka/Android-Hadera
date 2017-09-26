package com.borka.taskappboris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText etUser;
    private EditText etPassword;
    private Button btLogin;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String user="", pswrd="",name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser=(EditText)findViewById(R.id.editTextUser);
        etPassword=(EditText)findViewById(R.id.editTextPassword);
        btLogin = (Button)findViewById(R.id.buttonLogin);
        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                    user = sharedPreferences.getString("user", "");
                    pswrd = sharedPreferences.getString("password", "");

//                    Toast.makeText(LoginActivity.this, user, Toast.LENGTH_LONG).show();
//                    Toast.makeText(LoginActivity.this, pswrd, Toast.LENGTH_LONG).show();
                    if (etUser.getText().toString().equals(user) && etPassword.getText().toString().equals(pswrd)) {

                        Intent intent = new Intent(LoginActivity.this, TaskActivity.class);
                        intent.putExtra("username", etUser.getText().toString());
                        startActivity(intent);
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login incorrect", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
