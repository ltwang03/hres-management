package edu.huflit.hres_management.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper    {

    public static final String DBName = "Userdata.db";

    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table Product(product_url TEXT  , product_name TEXT primary key, product_price INT , product_type TEXT , product_descripe TEXT )");
        MyDB.execSQL("create Table Ordering(table_number TEXT ,check_in_time TEXT,check_out_time TEXT)");
        MyDB.execSQL("create Table Tablee(location TEXT , booked boolean) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists Product");
        MyDB.execSQL("drop Table if exists Ordering");
        MyDB.execSQL("drop Table if exists Tablee");

    }
    public boolean insertTableeData(String location, Boolean booked) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("location",location);
        contentValues.put("booked",booked);
        long result = MyDB.insert("Tablee" , null,contentValues);
        if(result == -1) return false;
        else
            return true;

    }
    public boolean insertOrderingData(String tableNumber, String checkInTime, String checkOutTime) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("table_number",tableNumber);
        contentValues.put("check_in_time",checkInTime);
        contentValues.put("check_out_time",checkOutTime);
        long result = MyDB.insert("Ordering" , null,contentValues);
        if(result == -1) return false;
        else
            return true;


    }
    public boolean updateOrderingData(String tableNumber, String checkInTime, String checkOutTime) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("table_number",tableNumber);
        contentValues.put("check_in_time",checkInTime);
        contentValues.put("check_out_time",checkOutTime);
        Cursor cursor  = MyDB.rawQuery("Select * from Ordering where tableNumber = ?", new String[] {tableNumber});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.update("Ordering" , contentValues , "tableNumber=?", new String[] {tableNumber});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public Boolean deleteOrderingData(String tableNumber ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();


        Cursor cursor  = MyDB.rawQuery("Select * from Ordering where tableNumber = ?", new String[] {tableNumber});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.delete("Ordering"  , "tableNumber=?", new String[] {tableNumber});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public Boolean insertProductData(String productImageUrl, String productName , String productPrice, String productType, String productDescripe ) {
          SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_url" , productImageUrl);
        contentValues.put("product_name" , productName);
        contentValues.put("product_price" , productPrice);
        contentValues.put("product_type" , productType);
        contentValues.put("product_descripe" , productDescripe);
        long result = MyDB.insert("Product",  null , contentValues);
        if(result == -1) return false;
        else
            return true;
    }
    public Boolean updateProductData(String productImageUrl, String productName , String productPrice, String productType, String productDescripe ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_url" , productImageUrl);
        contentValues.put("product_name" , productName);
        contentValues.put("product_price" , productPrice);
        contentValues.put("product_type" , productType);
        contentValues.put("product_descripe" , productDescripe);
        Cursor cursor  = MyDB.rawQuery("Select * from Product where productName = ?", new String[] {productName});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.update("Product" , contentValues , "productName=?", new String[] {productName});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public Boolean deleteProductData(String productName ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();


        Cursor cursor  = MyDB.rawQuery("Select * from Product where productName = ?", new String[] {productName});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.delete("Product"  , "productName=?", new String[] {productName});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public Cursor getProductData() {
        SQLiteDatabase MyDB = this.getWritableDatabase();


        Cursor cursor  = MyDB.rawQuery("Select * from Product1 ",null    );
        return cursor;

    }
}

//    public Boolean checkUsername(String username) {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?" , new String[] {username});
//        if(cursor.getCount() > 0)
//            return true;
//        else
//            return false;
//    }
//    public Boolean checkUsernameandpassword( String username ,String password) {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ? " , new String[] {  username,password});
//        if(cursor.getCount() > 0)
//            return true;
//            else
//                return false;
//        }
//
//
//    }


    

