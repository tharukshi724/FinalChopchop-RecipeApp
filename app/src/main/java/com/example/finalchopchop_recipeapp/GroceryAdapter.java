package com.example.finalchopchop_recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryHolder> {

    Context c;
    ArrayList<List> model;
    ImageView icheck;
    boolean isSelected= false;
    DatabaseReference dbRef;
    EditText listname;



    public GroceryAdapter(ArrayList<List> model) {

        this.model = model;
    }



    @NonNull
    @Override
    public GroceryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.g_row,null);

        return new GroceryHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GroceryHolder holder, int position) {


        holder.name.setText(model.get(position).getName());
        holder.price.setText(String.valueOf(model.get(position).getPrice()));




    /*  dbRef=FirebaseDatabase.getInstance().getReference().child("Ref").child(model.get(position).getName());


     dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {
             ArrayList<Model> ret = new ArrayList<>();
             for(DataSnapshot ds : snapshot.getChildren()){

                Model m = ds.getValue(Model.class);
                ret.add(m);

             }
         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
     });
*/

    }



    @Override
    public int getItemCount() {
        return model.size();
    }



    public void  deleteItem(int position){
        model.remove(position);
        notifyItemRemoved(position);
    }
}
