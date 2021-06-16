package com.lava.bakeanddeliver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductView extends AppCompatActivity {
    String id,category,productname,cost;
    int quantity=0;
    ImageView proimage;
    Button addtocart;
    ImageButton plusbut,minusbut;
    TextView proname,quant,cost1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        getIntentData();
        proname = findViewById(R.id.nameview);
        quant = findViewById(R.id.quantview);
        cost1 = findViewById(R.id.costview);
        addtocart = findViewById(R.id.cartbutton);
        proimage = findViewById(R.id.productimage);
        String stringvalue="@drawable/"+productname;
        int imgid = getResources().getIdentifier(stringvalue,null,getPackageName());
        proimage.setImageResource(imgid);
        proname.setText("Name:"+productname);
        quant.setText("Quantity:0");
        cost1.setText("Price:"+cost);
        plusbut = findViewById(R.id.imageButton);
        plusbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                String str = "Quantity:"+ quantity;
                quant.setText(str);
            }
        });
        minusbut = findViewById(R.id.imageButton2);
        minusbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity>0){
                    quantity--;
                    String str = "Quantity:"+ quantity;
                    quant.setText(str);
                }
                else{
                    Toast.makeText(ProductView.this,"Negative quantity",Toast.LENGTH_SHORT).show();
                }
            }
        });
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductData db = new ProductData(ProductView.this);
                db.addproductstocart(quantity,Integer.parseInt(cost),productname);
            }
        });

    }
    void getIntentData() {

        if (getIntent().hasExtra("id") && getIntent().hasExtra("category") && getIntent().hasExtra("productname") && getIntent().hasExtra("cost")) {
            id = getIntent().getStringExtra("id");
            category = getIntent().getStringExtra("category");
            productname = getIntent().getStringExtra("productname");
            cost = getIntent().getStringExtra("cost");


        }
        else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
}