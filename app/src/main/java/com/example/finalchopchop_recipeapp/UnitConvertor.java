package com.example.finalchopchop_recipeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class UnitConvertor extends AppCompatActivity {

    Spinner spinner;
    Button tick,convert,clear;
    EditText editTextNumber2, editTextNumber1;
    int ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_convertor);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner = (Spinner) findViewById(R.id.SpinUnit);
        editTextNumber1 = (EditText) findViewById(R.id.editTextNumber1);
        editTextNumber2 = (EditText) findViewById(R.id.editTextNumber2);
        tick=findViewById(R.id.btntick);
        convert=findViewById(R.id.btnconvert);
        clear=findViewById(R.id.btnClear);

        editTextNumber1.setText(null);
        editTextNumber2.setText(null);

        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filter = spinner.getSelectedItem().toString();
                if(filter.equals("lbs - kg")){

                    editTextNumber1.setHint("lbs");
                    editTextNumber2.setHint("kg");
                    ref=2;
                }

                else if(filter.equals("kg - g")){

                    editTextNumber1.setHint("kg");
                    editTextNumber2.setHint("g");
                    ref=1;
                }

                else if(filter.equals("g - mg")){

                    editTextNumber1.setHint("g");
                    editTextNumber2.setHint("mg");
                    ref=3;
                }
                else if(filter.equals("l - ml")){

                    editTextNumber1.setHint("l");
                    editTextNumber2.setHint("ml");
                    ref=4;
                }
                else if(filter.equals("table spoon - ml")){

                    editTextNumber1.setHint("spoon");
                    editTextNumber2.setHint("ml");
                    ref=5;
                }
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(ref){

                    case 1:
                        if(editTextNumber1!=null){
                           double kg = Double.parseDouble(String.valueOf(editTextNumber1.getText()));
                           editTextNumber2.setText(String.valueOf(kg*1000));

                        }
                        else{
                            double g = Double.parseDouble(String.valueOf(editTextNumber2.getText()));
                            editTextNumber1.setText(String.valueOf(g/1000));
                        }
                        break;
                    case 2:
                        if(editTextNumber1!=null){
                            double lbs = Double.parseDouble(String.valueOf(editTextNumber1.getText()));
                            editTextNumber2.setText(String.valueOf(lbs*0.45359237));
                        }
                        else{
                            double kg = Double.parseDouble(String.valueOf(editTextNumber2.getText()));
                            editTextNumber1.setText(String.valueOf(kg/0.45359237));
                        }
                        break;
                    case 3:
                        if(editTextNumber1!=null){
                            double g = Double.parseDouble(String.valueOf(editTextNumber1.getText()));
                            editTextNumber2.setText(String.valueOf(g*1000));
                        }
                        else{
                            double mg = Double.parseDouble(String.valueOf(editTextNumber2.getText()));
                            editTextNumber1.setText(String.valueOf(mg/1000));
                        }
                        break;
                    case 4:
                        if(editTextNumber1!=null){
                            double l = Double.parseDouble(String.valueOf(editTextNumber1.getText()));
                            editTextNumber2.setText(String.valueOf(l*1000));
                        }
                        else{
                            double ml = Double.parseDouble(String.valueOf(editTextNumber2.getText()));
                            editTextNumber1.setText(String.valueOf(ml/1000));
                        }
                        break;
                    case 5:
                        if(editTextNumber1!=null){
                            double spoon = Double.parseDouble(String.valueOf(editTextNumber1.getText()));
                            editTextNumber2.setText(String.valueOf(spoon*14.7868));
                        }
                        else{
                            double ml = Double.parseDouble(String.valueOf(editTextNumber2.getText()));
                            editTextNumber1.setText(String.valueOf(ml/14.7868));
                        }
                        break;
                    default:

                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber1.setText("");
                editTextNumber2.setText("");
            }
        });


    }


}