package com.borka.kidgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Personald extends Activity {

    EditText etfname, etlastname, etusername, etpassword;
    Button btSave;
    Player nplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personald);

        etfname = (EditText) findViewById(R.id.etname);
        etlastname = (EditText) findViewById(R.id.etlastname);
        etusername = (EditText) findViewById(R.id.etusernamereg);
        etpassword = (EditText) findViewById(R.id.etpasswordreg);
        btSave = (Button) findViewById(R.id.btsave);
        final DBHandler db = new DBHandler(this);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fn = etfname.getText().toString();
                String ln = etlastname.getText().toString();
                String un = etusername.getText().toString();
                String pass = etpassword.getText().toString();
                int score = 0;
                String dateuser = "";
                nplayer = new Player(fn, ln, un, pass, score, dateuser);
                db.addplayer(nplayer);

            }
        });
        //addplayer

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_personald, menu);
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

