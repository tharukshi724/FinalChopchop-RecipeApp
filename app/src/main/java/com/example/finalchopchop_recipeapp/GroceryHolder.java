package com.example.finalchopchop_recipeapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryHolder<ItemLongClickListner> extends RecyclerView.ViewHolder {


    TextView name;
    TextView price;
    ImageView idelete;
    View mview;



    GroceryHolder(@NonNull View itemView) {
        super(itemView);


        this.name = itemView.findViewById(R.id.text1);
        this.price = itemView.findViewById(R.id.text2);

       mview = itemView;





    }




}
