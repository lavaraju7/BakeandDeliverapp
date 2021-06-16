package com.lava.bakeanddeliver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.ArrayList;

public class ShowCart extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductData myDB;
    ArrayList<String> id, productname;
    ArrayList<Integer> quantity, cost;
    CustomAdapter2 customAdapter;
    Button order;
    TextView totalcost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        myDB = new ProductData(ShowCart.this);
        id = new ArrayList<>();
        productname = new ArrayList<>();
        quantity = new ArrayList<>();
        cost = new ArrayList<>();
        displayData();
        recyclerView = findViewById(R.id.cyclerView);
        order = findViewById(R.id.order);
        String orderdetails="";
        for(int i=0;i<productname.size();i++){
            orderdetails+= MessageFormat.format("{0}:{1}\n", productname.get(i), quantity.get(i).toString());
        }
        totalcost = findViewById(R.id.totalcost);
        int total=0;
        for(int i=0;i<productname.size();i++){
            total+=quantity.get(i)*cost.get(i);
        }
        totalcost.setText("Total Cost:"+total);

        String finalOrderdetails = orderdetails;
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsent = new Intent("android.intent.action.MAIN");
                newsent.setAction(Intent.ACTION_VIEW);
                newsent.setPackage("com.whatsapp");
                String url = "https://api.whatsapp.com/send?phone=" + "+917075072335" +"&text="+ finalOrderdetails;
                newsent.setData(Uri.parse(url));
                if(newsent.resolveActivity(getPackageManager())!=null){
                    startActivity(newsent);
                }
            }
        });

        customAdapter = new CustomAdapter2(ShowCart.this, this, id, productname, quantity, cost);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowCart.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void displayData() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                productname.add(cursor.getString(3));
                quantity.add(cursor.getInt(1));
                cost.add(cursor.getInt(2));
            }
        }

    }
}