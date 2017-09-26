package com.borka.weekendtarg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btSave,btCheck;
    private TextView tvScore,tvGame,tvEqual,tvMultiply,tvFirst,tvSecond;
    private EditText etName,etFamily,etResult;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSave = (Button)findViewById(R.id.btSave);
        btCheck = (Button)findViewById(R.id.btCheck);
        etName = (EditText) findViewById(R.id.etName);
        etFamily = (EditText) findViewById(R.id.etFamily);
        etResult = (EditText) findViewById(R.id.etResult);
        tvFirst = (TextView)findViewById(R.id.tvFirstNum);
        tvSecond = (TextView)findViewById(R.id.tvSecNum);
        tvGame = (TextView)findViewById(R.id.tvName);
        tvMultiply = (TextView)findViewById(R.id.tvMultiply);
        tvScore = (TextView)findViewById(R.id.tvScore);
        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);


        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString("name", ""); // 0 = Default value
        String family = sharedPreferences.getString("family", "");
        String score  = sharedPreferences.getString("score", ""); // 0 = Default value
        tvScore.setText(score);
        etName.setText(name);
        etFamily.setText(family);
        setRandomValues();


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                if(etName.getText().length()>0&&etFamily.getText().length()>0)
                {

                    editor.putString("name", etName.getText().toString());
                    editor.putString("family", etFamily.getText().toString());
                    tvScore.setText("0");
                    editor.putString("score", tvScore.getText().toString());
                    editor.commit();
                    Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"No data to save",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                int updScore;

                Vibrator vibrator = (Vibrator)MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);

                if(etResult!=null)
                {
                    int first = Integer.parseInt(tvFirst.getText().toString());
                    int second = Integer.parseInt(tvSecond.getText().toString());
                    int result =first*second;

                    if ((Integer.parseInt(etResult.getText().toString()) == result) )
                    {
                        Toast.makeText(MainActivity.this, "Bingo !!!", Toast.LENGTH_SHORT).show();
                       updScore= (Integer.parseInt(tvScore.getText().toString()))+10;

                     }

                    else
                    {
                        Toast.makeText(MainActivity.this, "Wrong .Correct is "+result, Toast.LENGTH_SHORT).show();
                        updScore= (Integer.parseInt(tvScore.getText().toString()))-1;

                        vibrator.vibrate(500);
                    }
                    tvScore.setText (""+updScore);
                    editor.putString("score", tvScore.getText().toString());
                    editor.commit();
                    etResult.setText("");
                    setRandomValues();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"No entered result",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

public void setRandomValues()
    {
        int first= (int)(Math.random()*10+1);
        tvFirst.setText(""+first);
        int second = (int)(Math.random()*10+1);
        tvSecond.setText(""+second);
    }

}
