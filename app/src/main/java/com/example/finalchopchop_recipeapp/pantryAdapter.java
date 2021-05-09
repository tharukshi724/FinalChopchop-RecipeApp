package com.example.finalchopchop_recipeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class pantryAdapter extends RecyclerView.Adapter<pantryAdapter.MyViewHolder> {

    List<Pantry> modelList;



    public pantryAdapter(List<Pantry> modelList) {
        this.modelList = modelList;




    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      Pantry pantry = this.modelList.get(position);

        holder.item.setText(pantry.getItem());
        holder.size.setText(pantry.getSize());


        try{
            String d1= this.modelList.get(position).getDate();
            SimpleDateFormat dates = new SimpleDateFormat("yyyy/MM/dd");
            String currentDate = dates.format(new Date());
            Date date1;
            Date date2;
            Long b1 = Long.valueOf(14);
            Long b2 = Long.valueOf(30);
            date1 = dates.parse(d1);
            date2 = dates.parse(currentDate);


            long  difference =  Math.abs(date1.getTime() - date2.getTime());


            long differenceDates = difference / (24 * 60 * 60 * 1000);

            String dayDifference = Long.toString(differenceDates);
            pantry.setDayDifference(dayDifference);








        } catch (Exception exception) {

        }
        holder.dayDifference.setText(pantry.getDayDifference()+"days");



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dia = DialogPlus.newDialog(holder.edit.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,600)
                        .create();

                View myview = dia.getHolderView();

                EditText item = myview.findViewById(R.id.item);
                EditText size = myview.findViewById(R.id.size);
                EditText date = myview.findViewById(R.id.date);
                Button btn = myview.findViewById(R.id.updateBtn);

                item.setText(pantry.getItem());
                size.setText(pantry.getSize());
                date.setText(pantry.getDate());

                dia.show();




            }
        });


    }





    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView item;
        TextView size;
        TextView dayDifference;

        Button edit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = (TextView)itemView.findViewById(R.id.text1);
            size = (TextView)itemView.findViewById(R.id.text3);
            dayDifference = (TextView)itemView.findViewById(R.id.text2);
            edit = itemView.findViewById(R.id.edit);
        }
    }

    public void deleteItem(int position){
        modelList.remove(position);
        // String key = keys.get(position);
        // DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Model");
        //ref.child(key).removeValue();
        //getSnapshots().getSnapshot(position).getReference().delete();
        notifyItemRemoved(position);

    }
}
