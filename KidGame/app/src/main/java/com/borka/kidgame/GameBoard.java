package com.borka.kidgame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GameBoard extends Activity {

    TextView tvname, tvdate, tvscore, tvlnum, tvrnum;
    Button btcheck, btsave;
    EditText etresult;
    DBHandler db;
    int x = 0, y = 0, gscore = 0;
    SharedPreferences sharedpreferences;
    public final String MyPREFERENCES = "MyPrefsPlayer";
    String userFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        tvname = (TextView) findViewById(R.id.tvname);
        tvdate = (TextView) findViewById(R.id.tvdate);
        tvscore = (TextView) findViewById(R.id.tvscore);
        tvlnum = (TextView) findViewById(R.id.tvlnum);
        tvrnum = (TextView) findViewById(R.id.tvrnum);
        btcheck = (Button) findViewById(R.id.btcheck);
        btsave = (Button) findViewById(R.id.btsave);
        etresult = (EditText) findViewById(R.id.etresult);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        db = new DBHandler(this);
        userFirstName = db.getplayername(sharedpreferences.getString("musername", ""));
        String mscore = db.getplayerScore(sharedpreferences.getString("musername", "0"));
        gscore=Integer.parseInt(mscore);
        tvname.setText(userFirstName);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        tvdate.setText(date);
        tvscore.setText(mscore);
        rundMe();

        btcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(etresult.getText().toString()) == x * y) {
                    gscore = gscore + 5;
                    tvscore.setText(gscore + "");
                } else {
                    gscore = gscore - 3;
                    tvscore.setText(gscore + "");
                }
                etresult.setText("");
                rundMe();
            }
        });


        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Player updateplayer = new Player("", "", sharedpreferences.getString("musername", ""), "", Integer.parseInt(tvscore.getText().toString()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                //new Player(fn, ln, un, pass, score, dateuser);
                //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                try {
                    int i=  db.updatePlayerScorenDate(updateplayer);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(GameBoard.this,"USER UPDATE....",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_game_board, menu);
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

    public void rundMe() {
        Random rand = new Random();
        x = rand.nextInt(5);
        y = rand.nextInt(5);

        tvlnum.setText(String.valueOf(x));
        tvrnum.setText(String.valueOf(y));
    }
}
