package com.lava.bakeanddeliver;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder>{
    private Context context;
    private ArrayList productname,quantity,cost;
    public ArrayList id;
    Activity activity;

    CustomAdapter2(Activity activity,Context context,ArrayList id,ArrayList productname,ArrayList quantity,ArrayList cost){
        this.activity=activity;
        this.context=context;
        this.id=id;
        this.productname=productname;
        this.quantity=quantity;
        this.cost=cost;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mycartitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String stringvalue = String.valueOf(productname.get(position));
        holder.name_txt.setText("Item: "+stringvalue);
        stringvalue="@drawable/"+stringvalue;
        int imgid = context.getResources().getIdentifier(stringvalue,null,context.getPackageName());
        holder.imageView.setImageResource(imgid);
        holder.quant_txt.setText("Quantity: "+quantity.get(position));
        holder.butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductData myDB = new ProductData(context);
                myDB.delete2(String.valueOf(id.get(position)));

            }
        });
    }

    @Override
    public int getItemCount() {
        return productname.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView quant_txt;
        TextView name_txt;
        ImageView imageView;
        Button butt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView3);
            name_txt = itemView.findViewById(R.id.textView2);
            quant_txt = itemView.findViewById(R.id.textView3);
            butt = itemView.findViewById(R.id.button);
            mainLayout = itemView.findViewById(R.id.main_layout2);
        }
    }
}
