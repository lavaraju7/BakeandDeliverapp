package com.lava.bakeanddeliver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Itemdisplay extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductData myDB;
    ArrayList<String> id,category,product_name,cost;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdisplay);
        myDB = new ProductData(Itemdisplay.this);
        id = new ArrayList<>();
        category = new ArrayList<>();
        product_name = new ArrayList<>();
        cost = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview1);
        myDB = new ProductData(this);
        displayData();
        customAdapter = new CustomAdapter(Itemdisplay.this,this,id,category,product_name,cost);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Itemdisplay.this));
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }
    void displayData(){
        Cursor cursor = myDB.readAllData(MenuScreen.cat);
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                category.add(cursor.getString(1));
                product_name.add(cursor.getString(2));
                cost.add(cursor.getString(3));
            }
        }
    }
}