package borka.com.gameanddb;

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
    private TextView tvGameName,tvMultiply,tvFirst,tvSecond,tvEqual,tvScore,tvName,tvDate;
    private EditText etResult;
    int maxNum;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etResult = (EditText) findViewById(R.id.etResult);
        btSave = (Button)findViewById(R.id.btSave);
        btCheck = (Button)findViewById(R.id.btCheck);
        tvName = (TextView) findViewById(R.id.tvFirstNum);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvFirst = (TextView)findViewById(R.id.tvFirstNum);
        tvSecond = (TextView)findViewById(R.id.tvSecNum);
        tvGameName = (TextView)findViewById(R.id.tvGameName);
        tvMultiply = (TextView)findViewById(R.id.tvMultiply);
        tvScore = (TextView)findViewById(R.id.tvScore);
        db = new DBHandler(this);

//        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        String name = sharedPreferences.getString("name", ""); // 0 = Default value
//        String family = sharedPreferences.getString("family", "");
//        String score  = sharedPreferences.getString("score", ""); // 0 = Default value
//        tvScore.setText(score);
//        etName.setText(name);
//        etFamily.setText(family);

        Player pl = new Player();
        pl= db.getPLayer(tvName.getText().toString());
        setRandomValues();


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences(SignupActivity.MY_PREFS_NAME, MODE_PRIVATE).edit();
                if(tvName.getText().length()>0&&tvDate.getText().length()>0&&tvScore.getText().length()>0)
                {

                    editor.putString("name", tvName.getText().toString());
                    editor.putString("date", tvDate.getText().toString());
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

                SharedPreferences.Editor editor = getSharedPreferences(SignupActivity.MY_PREFS_NAME, MODE_PRIVATE).edit();
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


        if(Integer.parseInt(tvScore.getText().toString()) < 30 ) {
            int maxNum =10;
        }
        else
        {
            int maxNum =100;
        }

        int first = (int) (Math.random() * maxNum + 1);
        tvFirst.setText("" + first);
        int second = (int) (Math.random() * maxNum + 1);
        tvSecond.setText("" + second);
    }

}