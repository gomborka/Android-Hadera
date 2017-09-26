package com.borka.secondexam;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText etName,etFamily,etPhone,etUser,etPaswrd;
    private Button btSave;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        btSave = (Button) findViewById(R.id.buttonClose);
        etName = (EditText) findViewById(R.id.editTextName);
        etFamily = (EditText) findViewById(R.id.editTextPassword);
        etPhone = (EditText) findViewById(R.id.editTextPhone);
        etUser = (EditText) findViewById(R.id.editTextUser);
        etPaswrd = (EditText) findViewById(R.id.editTextPswrd);

//        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        String name = sharedPreferences.getString("name", ""); // 0 = Default value
//        String family = sharedPreferences.getString("family", "");
//        String user = sharedPreferences.getString("user", ""); // 0 = Default value
//        String phone = sharedPreferences.getString("phone", ""); // 0 = Default value
//        String pswrd = sharedPreferences.getString("password", ""); // 0 = Default value
//        etUser.setText(user);
//        etName.setText(name);
//        etFamily.setText(family);
//        etPhone.setText(phone);
//        etPaswrd.setText(pswrd);


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                if (etName.getText().length() > 0 && etFamily.getText().length() > 0 && etUser.getText().length() > 0 && etPhone.getText().length() > 0 && etPaswrd.getText().length() > 0) {

                    editor.putString("name", etName.getText().toString());
                    editor.putString("family", etFamily.getText().toString());
                    editor.putString("user", etUser.getText().toString());
                    editor.putString("phone", etPhone.getText().toString());
                    editor.putString("password", etPaswrd.getText().toString());

                    editor.commit();
                    Toast.makeText(SignupActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    SignupActivity.this.finish();
                } else {
                    Toast.makeText(SignupActivity.this, "Fill empty field", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
