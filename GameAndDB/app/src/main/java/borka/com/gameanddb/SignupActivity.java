package borka.com.gameanddb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private EditText etName,etFamily,etUser,etPaswrd;
    private Button btSave;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        btSave = (Button) findViewById(R.id.buttonClose);
        etName = (EditText) findViewById(R.id.editTextName);
        etFamily = (EditText) findViewById(R.id.editTextFamily);
        etUser = (EditText) findViewById(R.id.editTextUser);
        etPaswrd = (EditText) findViewById(R.id.editTextPswrd);
        db = new DBHandler(this);


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    if (etUser.getText().length() > 0 && etPaswrd.getText().length() > 0) {
                        editor.putString("name", etName.getText().toString());
                        editor.putString("family", etFamily.getText().toString());
                        editor.putString("user", etUser.getText().toString());
                        editor.putString("password", etPaswrd.getText().toString());
                        editor.putString("score", "0");
                        editor.putString("date", etPaswrd.getText().toString());

                        editor.commit();
                        Toast.makeText(SignupActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                        Player pl = new Player();
                        pl.setName(etName.getText().toString());
                        pl.setPassword(etPaswrd.getText().toString());
                        pl.setUser(etUser.getText().toString());
                        pl.setFamily(etFamily.getText().toString());
                        pl.setDate("date");
                        pl.setScore(0);

                        db.addPlayer(pl);
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        SignupActivity.this.finish();
                    } else {
                        Toast.makeText(SignupActivity.this, "Fill user/password field", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
    }


}
