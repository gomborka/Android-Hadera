package com.borka.dynamicview;

import android.app.Activity;

import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    LinearLayout container;
    Button btn;
    private TextView tv;
    EditText ed;
    int count = 0;
    int fullCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout) findViewById(R.id.container);
        btn = (Button) findViewById(R.id.button);
        ed = (EditText) findViewById(R.id.editText);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv = new TextView(MainActivity.this);
                tv.setText("" + count + " " + ed.getText());
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(20f);
                if (count % 2 == 0) {
                    tv.setBackgroundColor(Color.BLUE);
                } else {
                    tv.setBackgroundColor(Color.GREEN);
                }

                count++;
                container.addView(tv);
                fullCount++;


                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv = new TextView(MainActivity.this);
                        Toast.makeText(MainActivity.this, ((TextView) view).getText() + " was removed ", Toast.LENGTH_SHORT).show();
                        tv.getBackground();

                        container.removeView(view);
                        reColor();
                    }
                });

            }
        });



}
    void reColor() {

        boolean odd = true;
        for (int i = 0; i < container.getChildCount(); i++) {
            ((TextView) container.getChildAt(i)).setTextColor((odd = !odd) ? Color.BLUE : Color.GREEN);
        }
    }
}