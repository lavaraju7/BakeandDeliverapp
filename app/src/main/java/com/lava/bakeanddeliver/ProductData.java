package com.lava.bakeanddeliver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class ProductData extends SQLiteOpenHelper {
    private static final  String COL_1 = "_id";
    private static final String COL_2 = "category";
    private static final String COL_3 = "productname";
    private static final String COL_4 = "cost";
    private static final String DATABASE_NAME = "products.db";
    private static final String TABLE_NAME = "productinfo";
    private static final String TABLE_NAME2 = "cartinfo";
    private static final int DATABASE_VERSION = 1;
    private Context context;

   public ProductData(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE productinfo (_id INTEGER primary key,category INTEGER not null,productname TEXT not null ,cost INTEGER not null)");
        db.execSQL("CREATE TABLE cartinfo (_id INTEGER primary key autoincrement,quantity INTEGER not null,cost INTEGER not null,productname TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate((db));
    }
    void addproducts(int id,int website, String username, int password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_1,id);
        contentvalues.put(COL_2, website);
        contentvalues.put(COL_3, username);
        contentvalues.put(COL_4, password);
        long result = db.insert(TABLE_NAME, null, contentvalues);
        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
    }
    void addproductstocart(int quantity,int cost,String productname){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put("quantity",quantity);
       contentValues.put("cost",cost);
       contentValues.put("productname",productname);
       long result = db.insert(TABLE_NAME2,null,contentValues);
        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(int category){

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE category = "+ category;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    Cursor readAllData(){

        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void delete2(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2,"_id=?",new String[]{row_id});

    }
    void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
    }

}
