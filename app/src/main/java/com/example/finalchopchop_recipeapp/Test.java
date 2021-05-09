package com.example.finalchopchop_recipeapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Test extends AppCompatActivity {

    EditText input;
    Button save;
    DatabaseReference dbRef;
    int i=0;
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        input=findViewById(R.id.input);
        save=findViewById(R.id.buttoninput);

        com.example.grocery.TestJava obj = new com.example.grocery.TestJava();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("test");
                try{
                    if(TextUtils.isEmpty(input.getText().toString()))
                        Toast.makeText(getApplicationContext(),"pls enter the input", Toast.LENGTH_LONG).show();
                    else{
                        obj.setIn(input.getText().toString().trim());
                        i++;
                        test = String.valueOf(i);
                        dbRef.child(test).setValue(obj);
                        Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}