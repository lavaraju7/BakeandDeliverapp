package com.lava.bakeanddeliver;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static android.content.Intent.getIntent;

public class MenuScreen extends AppCompatActivity implements View.OnClickListener{
    private CardView cards1, cards2, cards3, cards4, cards5, cards6, cards7, cards8, cards9;
    public  static int cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);
        cards1 = findViewById(R.id.card1);
        cards2 = findViewById(R.id.card2);
        cards3 = findViewById(R.id.card3);
        cards4 = findViewById(R.id.card4);
        cards5 = findViewById(R.id.card5);
        cards6 = findViewById(R.id.card6);
        cards7 = findViewById(R.id.card7);
        cards8 = findViewById(R.id.card8);
        cards9 = findViewById(R.id.card9);
        cards1.setOnClickListener(this);
        cards2.setOnClickListener(this);
        cards3.setOnClickListener(this);
        cards4.setOnClickListener(this);
        cards5.setOnClickListener(this);
        cards6.setOnClickListener(this);
        cards7.setOnClickListener(this);
        cards8.setOnClickListener(this);
        cards9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    Intent i = new Intent(MenuScreen.this,Itemdisplay.class);
    switch (v.getId()){
        case R.id.card1:cat=1; break;
        case R.id.card2:cat=2; break;
        case R.id.card3:cat=3; break;
        case R.id.card4:cat=4; break;
        case R.id.card5:cat=5; break;
        case R.id.card6:cat=6; break;
        case R.id.card7:cat=7; break;
        case R.id.card8:cat=8; break;
        case R.id.card9:cat=9; break;
    }
    startActivity(i);
    }

}
