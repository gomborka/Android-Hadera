package com.borka.secondexam;

       import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    private EditText etName,etFamily,etPhone,etUser,etPasswrd;
    private Button btClose;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        btClose = (Button) findViewById(R.id.buttonClose);
        etName = (EditText) findViewById(R.id.editTextName);
        etFamily = (EditText) findViewById(R.id.editTextPassword);
        etPhone = (EditText) findViewById(R.id.editTextPhone);
        etUser = (EditText) findViewById(R.id.editTextUser);
        etPasswrd = (EditText) findViewById(R.id.editTextPswrd);


        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String family = sharedPreferences.getString("family", "");
        String user  = sharedPreferences.getString("user", "");
        String phone  = sharedPreferences.getString("phone", "");
        String pswrd  = sharedPreferences.getString("password", "");
        etName.setText(name);
        etFamily.setText(family);
        etUser.setText(user);
        etPhone.setText(phone);
        etPasswrd.setText(pswrd);

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
//                startActivity(intent);
                DetailsActivity.this.finish();
            }
        });
    }
}
