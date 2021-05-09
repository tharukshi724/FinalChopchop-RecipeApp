package com.example.finalchopchop_recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdditemsToList extends AppCompatActivity {

    DatabaseReference dbRef,dbRef1;
    RecyclerView recyclerView;
    Button nameSave,save;
    ImageView itemSave;
    EditText listName,itemName,itemPrice;
    ArrayList<ItemModel> list= new ArrayList<>();
    TextView item1,item2,item3,item4,price1,price2,price3,price4;
    int itemCount=1;
    int tempprice=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);

        save=findViewById(R.id.btnsave);

        itemSave=findViewById(R.id.Tick);

        listName=findViewById(R.id.inputname);
        itemName=findViewById(R.id.inputItemName);
        itemPrice=findViewById(R.id.InputPrice);

        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);
        item3=findViewById(R.id.item3);
        item4=findViewById(R.id.item4);

        price1=findViewById(R.id.price1);
        price2=findViewById(R.id.price2);
        price3=findViewById(R.id.price3);
        price4=findViewById(R.id.price4);

        com.example.grocery.List indexRef = new com.example.grocery.List();

        itemSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tempName=listName.getText().toString().trim();

                String item =itemName.getText().toString().trim();
                int price= Integer.parseInt(itemPrice.getText().toString().trim());
               tempprice=tempprice+ Integer.parseInt(itemPrice.getText().toString().trim());

                ItemModel obj=new ItemModel(item,price);

                itemToView(item,price);

                list.add(obj);
                Toast.makeText(getApplicationContext(),"Item added", Toast.LENGTH_SHORT).show();
                itemName.setText("");
                itemPrice.setText("");
                itemCount++;
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("grocery List");

                try{
                    if(TextUtils.isEmpty(listName.getText().toString().trim()))
                        Toast.makeText(getApplicationContext(),"pls enter the List Name", Toast.LENGTH_LONG).show();
                    else{
                        dbRef.child(listName.getText().toString().trim()).setValue(list);
                        indexRef.setName(listName.getText().toString().trim());
                        indexRef.setPrice(tempprice);
                        tempprice=0;


                        list.clear();
                        itemCount=1;
                        textViewClear();
                        listName.setText("");
                        Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(com.example.grocery.AdditemsToList.this, com.example.grocery.GroceryListMainActivity.class));
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG).show();
                }

                dbRef1 = FirebaseDatabase.getInstance().getReference().child("Ref");
                try{
                    dbRef1.child(indexRef.getName()).setValue(indexRef);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"error from ref", Toast.LENGTH_LONG).show();
                }

            }
        });

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemName.setText(item1.getText());
                itemPrice.setText(price1.getText());
                itemCount=1;
                tempprice=tempprice- Integer.parseInt(String.valueOf(price1.getText()));
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemName.setText(item2.getText());
                itemPrice.setText(price2.getText());
                itemCount=2;
                tempprice=tempprice- Integer.parseInt(String.valueOf(price2.getText()));
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemName.setText(item3.getText());
                itemPrice.setText(price3.getText());
                itemCount=3;
                tempprice=tempprice- Integer.parseInt(String.valueOf(price3.getText()));
            }
        });
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemName.setText(item4.getText());
                itemPrice.setText(price4.getText());
                itemCount=4;
                tempprice=tempprice- Integer.parseInt(String.valueOf(price4.getText()));
            }
        });

    }

    public void itemToView(String name, int price){

        if(itemCount==1){
            item1.setText(name);
            price1.setText(String.valueOf(price));
        }
        else if(itemCount==2){
            item2.setText(name);
            price2.setText(String.valueOf(price));
        }
        else if(itemCount==3){
            item3.setText(name);
            price3.setText(String.valueOf(price));
        }
        else{
            item4.setText(name);
            price4.setText(String.valueOf(price));
        }
    }

    public void textViewClear(){
        item1.setText("");
        price1.setText("");
        item2.setText("");
        price2.setText("");
        item3.setText("");
        price3.setText("");
        item4.setText("");
        price4.setText("");

    }
}