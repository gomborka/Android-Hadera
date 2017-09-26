package com.borka.targthread;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private EditText etTimer;
    private Button btAsync;
    private Button btPost;
    private Button btPostDelayed;
    private Button btHandler;
    private Button btRunOnUI;
    private ProgressBar progressBarSec;
    private int timer= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);
        etTimer= (EditText)findViewById(R.id.editTextTimer);
        btAsync =(Button)findViewById(R.id.btAsync);
        btPost =(Button)findViewById(R.id.btPost);
        btPostDelayed =(Button)findViewById(R.id.btPostDelayed);
        btHandler =(Button)findViewById(R.id.btHandler);
        btRunOnUI =(Button)findViewById(R.id.btRunOnUI);
        progressBarSec =(ProgressBar)findViewById(R.id.progressBar);
        btAsync.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view){

        progressBarSec.setVisibility(View.VISIBLE);
        progressBarSec.setProgress(0);
        timer= Integer.parseInt(etTimer.getText().toString());

        // detect the view that was "clicked"
        switch (view.getId()) {
            case R.id.btAsync:
                new AsyncOperation().execute("");
                break;
//            case R.id.btPost:
//                new PostOperation().execute("");
//                break;
            case R.id.btRunOnUI:
                OnUiThreadOperation();
                Toast.makeText(this,)
                break;

        }
           }

    private void OnUiThreadOperation() {

        new Thread() {
            public void run() {
                for(int i=0; i < 10;i++)
                {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                tv.setText("OnUiThread");
                            }
                        });
                        Thread.sleep(200);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                  }
            }
        }.start();
    }
    private class AsyncOperation extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            for(int i=0;i<timer;i++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(timer-i);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            tv.setText("Boris Goman");
            progressBarSec.setVisibility(View.GONE);

        }

        @Override
        protected void onPreExecute() {
            tv.setText("Starting Async..");

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tv.setText("" + values[0]);
            progressBarSec.setProgress(values[0]);
        }
    }


}
