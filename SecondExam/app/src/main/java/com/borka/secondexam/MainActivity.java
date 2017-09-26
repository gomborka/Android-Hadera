package com.borka.secondexam;


        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btRegister, btLogin, btDetails;
    private EditText edUser, edPswrd;
    private TextView tvResult;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String user="", pswrd="",name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView count;
        btRegister = (Button) findViewById(R.id.buttonRegister);
        btDetails = (Button) findViewById(R.id.buttonDetails);
        btLogin = (Button) findViewById(R.id.buttonLogin);
        edUser = (EditText) findViewById(R.id.editTextUser);
        edPswrd = (EditText) findViewById(R.id.editTextPassword);
        tvResult = (TextView) findViewById(R.id.textViewResult);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                     user = sharedPreferences.getString("user", "");
                     pswrd = sharedPreferences.getString("password", "");
                    name = sharedPreferences.getString("name", "");
                    Toast.makeText(MainActivity.this, user, Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, pswrd, Toast.LENGTH_LONG).show();
                    if (edUser.getText().toString().equals(user) && edPswrd.getText().toString().equals(pswrd)) {

                        tvResult.setVisibility(View.VISIBLE);
                        tvResult.setText("Shalom " + name);
                    } else {
                        Toast.makeText(MainActivity.this, "Login incorrect", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    //   @Override
      public boolean onOptionsItemSelected(MenuItem item) {
     switch (item.getItemId()) {
        case R.id.menuItemLogin:

            Toast.makeText(MainActivity.this, user, Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this, pswrd, Toast.LENGTH_LONG).show();
            if (edUser.getText().toString().equals(user) && edPswrd.getText().toString().equals(pswrd)) {

                tvResult.setVisibility(View.VISIBLE);
                tvResult.setText("Shalom " + name);
            } else {
                Toast.makeText(MainActivity.this, "Login incorrect", Toast.LENGTH_LONG).show();
            }
            return true; // Handled.
        case R.id.menuItemRegister:
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
            return true;
        case R.id.menuItemDetails:
            Intent intent1 = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(intent1);
            return true;
        case R.id.menuItemExit:
            Toast.makeText(this, "Exit...", Toast.LENGTH_SHORT).show();
            return true;
    }
          return false; // Not handled.
      }

}
 //   @Override
 //   public boolean onOptionsItemSelected(MenuItem item) {
        //       switch(item.getItemId()) {
//        TextView count;
//        case R.id.add:
//            count=(TextView)findViewById(R.id.textView);
//            count.setText("Add is clicked");
//            return(true);
//        case R.id.reset:
//            count=(TextView)findViewById(R.id.textView);
//            count.setText("Nothing is selected");
//            return(true);
//        case R.id.about:
//            Toast.makeText(this, R.string.about, Toast.LENGTH_LONG).show();
//            return(true);
//        case R.id.exit:
//            finish();
//            return(true);
//
//    }
//        return(super.onOptionsItemSelected(item));
 //         }
//}