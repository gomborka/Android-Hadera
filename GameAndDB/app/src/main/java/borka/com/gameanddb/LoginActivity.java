package borka.com.gameanddb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btSignup, btLogin;
    private EditText edUser, edPswrd;
    private CheckBox cbRemember;
    String user="", pswrd="",autoLogin="";

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btSignup = (Button) findViewById(R.id.buttonSignup);
        btLogin = (Button) findViewById(R.id.buttonLogin);
        edUser = (EditText) findViewById(R.id.editTextUser);
        edPswrd = (EditText) findViewById(R.id.editTextPswrd);
        cbRemember= (CheckBox) findViewById(R.id.checkBoxRemember);


            SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            user = sharedPreferences.getString("user", "");
            pswrd = sharedPreferences.getString("password", "");
            autoLogin= sharedPreferences.getString("autologin", "");

        Toast.makeText(LoginActivity.this, user, Toast.LENGTH_SHORT).show();
        Toast.makeText(LoginActivity.this, pswrd, Toast.LENGTH_SHORT).show();
        Toast.makeText(LoginActivity.this, autoLogin, Toast.LENGTH_SHORT).show();

            if(autoLogin.equals("saved"))
                edUser.setText(user);
                edPswrd.setText(pswrd);



        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (edUser.getText().toString().equals(user) && edPswrd.getText().toString().equals(pswrd)) {
                         if(cbRemember.isChecked()) {
                             SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                             editor.putString("user", edUser.getText().toString());
                             editor.putString("password", edPswrd.getText().toString());
                             editor.putString("login", "saved");
                             editor.commit();
                             Toast.makeText(LoginActivity.this,"Saved",Toast.LENGTH_SHORT).show();
                         }
                         else{
                             SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                             editor.putString("login", "empty");
                             editor.commit();
                         }

                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "Login incorrect", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                if (isChecked && edUser.getText().toString().length() > 0 && edPswrd.getText().toString().length() > 0) {

                    editor.putString("user", edUser.getText().toString());
                    editor.putString("password", edPswrd.getText().toString());
                    editor.putBoolean("autologin", cbRemember.isChecked());
                    editor.commit();
                }
                if (!isChecked) {
                    editor.putString("user", edUser.getText().toString());
                    editor.putString("password", edPswrd.getText().toString());
                    editor.putBoolean("autologin", false);
                    editor.commit();
                }
            }
        });


    }
}
