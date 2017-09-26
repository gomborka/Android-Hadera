package com.borka.taskappboris;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskActivity  extends ListActivity {

    ArrayList<String> listTask=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView list;
    private TextView tvTaskCaption;
    private  TextView tvUserCaption;
    private EditText etTask;
    private Button btSubmit;
    int clickCounter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tvTaskCaption= (TextView)findViewById(R.id.textViewTaskCaption);
        tvUserCaption= (TextView)findViewById(R.id.textViewUserCaption);
        btSubmit =(Button)findViewById(R.id.buttonSubmit);
        etTask = (EditText)findViewById(R.id.editTextTask);
        list = (ListView)findViewById(R.id.listViewTask);

        adapter=new ArrayAdapter<String>(this,R.layout.task_item,listTask);
        setListAdapter(adapter);

 /// Possible by Shared Preference but decided by intent
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String getUser = (String) bd.get("username");
            tvUserCaption.setText(getUser);
        }


///////////////////////////////////////////////////
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    if (etTask.getText().length() > 0) {
                        listTask.add(etTask.getText().toString());
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(TaskActivity.this, "No Task entered", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }
}
