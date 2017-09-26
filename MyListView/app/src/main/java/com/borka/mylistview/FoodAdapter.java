package com.borka.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Comp14 on 24/08/2017.
 */

public class FoodAdapter extends ArrayAdapter<Food> {
    private LayoutInflater inflater;

    public FoodAdapter(Context context, ArrayList<Food> food) {
        super(context,0, food);
        //Getinflater object
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView (int position, View convertView, ViewGroup parent)
    {
        //Inflate the layout
        LinearLayout linearLayoutRoot = (LinearLayout)inflater.inflate(R.layout.food_item, null);
        ImageView imageViewFood = (ImageView) linearLayoutRoot.findViewById(R.id.imageViewFood);
        TextView textViewName = (TextView) linearLayoutRoot.findViewById(R.id.textViewName);

        //The current item to display
        Food food = getItem(position);
        imageViewFood.setImageResource(food.getImageID());
        textViewName.setText(food.getName());

        return  linearLayoutRoot;
    }


}