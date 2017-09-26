package com.borka.targilaugust;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUserName, etPassword;
    CheckBox cbRememberMe;
    Button btlogin , btReg;
    DBHandler db;
    public   final String MyPREFERENCES = "MyPrefsPlayer";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.etusernamelogin);
        etPassword = (EditText) findViewById(R.id.etpasswordlogin);
        cbRememberMe = (CheckBox) findViewById(R.id.cbremmber);
        btlogin = (Button) findViewById(R.id.btlogin);
        btReg = (Button) findViewById(R.id.btreg);

        try {
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            if (sharedpreferences.getBoolean("cbstatus", false)) {
                cbRememberMe.setChecked(true);
            }
            etUserName.setText(sharedpreferences.getString("username", ""));
            etPassword.setText(sharedpreferences.getString("password", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        db = new DBHandler(this);
        cbRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                if (isChecked && etUserName.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0) {

                    editor.putString("username", etUserName.getText().toString());
                    editor.putString("password", etPassword.getText().toString());
                    editor.putBoolean("cbstatus", cbRememberMe.isChecked());
                    editor.commit();
                }
                if (!isChecked) {
                    editor.putString("username", "");
                    editor.putString("password", "");
                    editor.putBoolean("cbstatus", false);
                    editor.commit();
                }
            }
        });

       // Getalldata();

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etUserName.getText().length() > 0 && etPassword.getText().length() > 0) {
                    String dbpass = db.getWorkerPass(etUserName.getText().toString());

                    if (dbpass.equals(etPassword.getText().toString()) && !etPassword.getText().toString().equals("")) {
                        Intent myIntent = new Intent(LoginActivity.this, WorkerActivity.class);
                        LoginActivity.this.startActivity(myIntent);
                        LoginActivity.this.finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "wrong", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "no data", Toast.LENGTH_LONG).show();

                }

            }
        });

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(LoginActivity.this, Personald.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
