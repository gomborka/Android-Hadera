package com.borka.cardlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    LinearLayout container;
     private LinearLayout layout;
    int column =5;
    int row =5;
    private ImageView iv;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout) findViewById(R.id.container);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1f);

        for (int i = 0; i < row; i++) {
            layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setLayoutParams(params);
               for (int j = 0; j< column; j++) {
                   iv = new ImageView(this);
                   iv.setImageResource(R.drawable.cardback);
                   iv.setLayoutParams(params);
                   iv.setTag(counter);
                   layout.addView(iv);
                     counter++;

                }
            container.addView(layout);
        }

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this ,iv.getTag().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
//   public class Clicks  implements View.OnClickListener
//   {
//
//       @Override
//       public void onClick(View view) {
//           Toast.makeText(MainActivity.this ,iv.getTag().toString(),Toast.LENGTH_SHORT).show();
//       }
//   }
}
