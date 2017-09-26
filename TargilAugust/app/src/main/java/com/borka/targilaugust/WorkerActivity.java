package com.borka.targilaugust;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

public class WorkerActivity extends AppCompatActivity {

    TextView tvWelcome;
    Button btEnter,btExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

    tvWelcome = (TextView)findViewById(R.id.textViewWelcome);
        btEnter= (Button) findViewById(R.id.buttonEnter);
        btExit = (Button)findViewById(R.id.buttonExit);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navBottom);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.mapNav:
                                selectedFragment = MapFragment.newInstance();
                                break;
                            case R.id.webNav:
                                selectedFragment = WebFragment.newInstance();
                                break;
                            case R.id.cameraNav:
                                selectedFragment = CameraFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.MainLinear, selectedFragment);

                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
//        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }


}
