package com.lava.bakeanddeliver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.Activity;

import static androidx.core.content.ContextCompat.startActivity;
import static com.lava.bakeanddeliver.R.id.menubutton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(menubutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
                Intent i = new Intent(v.getContext(),MenuScreen.class);
                startActivity(i);
            }

            private void add() {
                ProductData mydatabase = new ProductData(MainActivity.this);
                mydatabase.delete();
                mydatabase.addproducts(1,1,"instagram_fore",300);
                mydatabase.addproducts(2,2,"gmail_foreground",300);
            }

        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cartbutton) {
            Intent savedpasswords = new Intent(MainActivity.this,ShowCart.class);
            startActivity(savedpasswords);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
