package com.borka.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listViewFood;
    private ArrayList<Food> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listViewFood = (ListView) findViewById(R.id.listViewFoodMain);

        foodList.add(new Food("Bread", R.drawable.bread));
        foodList.add(new Food("Cheese", R.drawable.cheeseflat));
        foodList.add(new Food("Ice Cream", R.drawable.icecream));
        foodList.add(new Food("Pizza", R.drawable.pizza));
        foodList.add(new Food("Sushi", R.drawable.suchi));

        final FoodAdapter foodAdapter = new FoodAdapter(this, foodList);
        listViewFood.setAdapter(foodAdapter);


        listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foodList.remove(position);
                foodAdapter.notifyDataSetChanged();

            }
        });
    }
}
