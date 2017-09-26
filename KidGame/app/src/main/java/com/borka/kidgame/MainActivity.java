package com.borka.kidgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    EditText etUserName, etPassword;
    CheckBox cbRememberMe;
    Button btlogin, btReg;
    DBHandler db;
    public   final String MyPREFERENCES = "MyPrefsPlayer";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            etUserName.setText(sharedpreferences.getString("musername", ""));
            etPassword.setText(sharedpreferences.getString("mpassword", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        db = new DBHandler(this);
        cbRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                if (isChecked && etUserName.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0) {

                    editor.putString("musername", etUserName.getText().toString());
                    editor.putString("mpassword", etPassword.getText().toString());
                    editor.putBoolean("cbstatus", cbRememberMe.isChecked());
                    editor.commit();
                }
                if (!isChecked) {
                    editor.putString("musername", "");
                    editor.putString("mpassword", "");
                    editor.putBoolean("cbstatus", false);
                    editor.commit();
                }
            }
        });

        Getalldata();

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etUserName.getText().length() > 0 && etPassword.getText().length() > 0) {
                    String dbpass = db.getplayerPass(etUserName.getText().toString());

                    if (dbpass.equals(etPassword.getText().toString()) && !etPassword.getText().toString().equals("")) {
                        Intent myIntent = new Intent(MainActivity.this, GameBoard.class);
                        MainActivity.this.startActivity(myIntent);
                        MainActivity.this.finish();

                    } else {
                        Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "no data", Toast.LENGTH_LONG).show();

                }

            }
        });

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, Personald.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    public void Getalldata() {
        List<Player> playerList = new ArrayList<Player>();
        playerList = db.getAllTable();

        for (Player tmpplay : playerList) {
            Log.i("gilsDB", tmpplay.id + "");
            Log.i("gilsDB", tmpplay.fname.toString());
            Log.i("gilsDB", tmpplay.lname.toString());
            Log.i("gilsDB", tmpplay.Date.toString());
            Log.i("gilsDB", tmpplay.username.toString());
            Log.i("gilsDB", tmpplay.password.toString());
            Log.i("gilsDB", tmpplay.score + " ");


        }
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
