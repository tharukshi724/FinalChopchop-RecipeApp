package com.example.finalchopchop_recipeapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroceryListMainActivity extends AppCompatActivity {


        FloatingActionButton addButton;
    RecyclerView recyclerview;
    com.example.grocery.GroceryAdapter myAdapter;
    DatabaseReference dbRef;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new com.example.grocery.GroceryAdapter( getMyList());
        recyclerview.setAdapter(myAdapter);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.grocery.GroceryListMainActivity.this,AdditemsToList.class));
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(com.example.grocery.GroceryListMainActivity.this);
                builder.setTitle("Delete Grocery List");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       myAdapter.deleteItem(viewHolder.getAdapterPosition());

                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("grocery List");
                        final DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Ref");
                        reference.child(String.valueOf(viewHolder.getItemId())).removeValue();
                        reference1.child(String.valueOf(viewHolder.getItemId())).removeValue();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                    }
                });

                builder.show();
            }
        }).attachToRecyclerView(recyclerview);

    }

    private ArrayList<List> getMyList(){


        ArrayList<List> allList = new ArrayList<>();

        dbRef=FirebaseDatabase.getInstance().getReference().child("Ref");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    List tempList =ds.getValue(List.class);
                    allList.add(tempList);
                }
                myAdapter = new com.example.grocery.GroceryAdapter(allList);
                recyclerview.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return allList;
    }

}