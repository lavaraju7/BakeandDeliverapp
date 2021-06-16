package com.lava.bakeanddeliver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id, category, product_name, cost;
    Activity activity;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList category, ArrayList product_name, ArrayList cost) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.category = category;
        this.product_name = product_name;
        this.cost = cost;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String stringvalue = String.valueOf(product_name.get(position));
        holder.productname.setText(stringvalue);
        stringvalue="@drawable/"+stringvalue;
        int imgid = context.getResources().getIdentifier(stringvalue,null,context.getPackageName());
        holder.productimage.setImageResource(imgid);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductView.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("category", String.valueOf(category.get(position)));
                intent.putExtra("productname", String.valueOf(product_name.get(position)));
                intent.putExtra("cost", String.valueOf(cost.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productname;
        ImageView productimage;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productimage = itemView.findViewById(R.id.productimage);
            productname = itemView.findViewById(R.id.productname);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}