package edu.huflit.hres_management.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import edu.huflit.hres_management.API.model.ProfileResponse;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Userdata.db";

    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        CreateDatabase(MyDB);
    }

    public void CreateDatabase(SQLiteDatabase MyDB) {
        String createTableProduct = ("CREATE TABLE Product" +
                "(product_id INTEGER primary key AUTOINCREMENT," +
                "product_url TEXT," +
                " product_name TEXT," +
                " product_price INT , product_type TEXT," +
                " product_descripe TEXT)");
        String createTableOrdering = ("CREATE TABLE Ordering" +
                "(id integer primary key autoincrement," +
                "table_number TEXT," +
                "product_url TEXT, "+
                "product_name TEXT, " +
                "product_amount INTEGER, " +
                "product_price INTEGER)");
        String createTableTableNumber = ("CREATE TABLE Tablee" +
                "(location TEXT primary key," +
                "customer_name TEXT," +
                "amount_customer TEXT," +
                "checkin TEXT," +
                " booked boolean) ");

        String createBillList = ("CREATE TABLE BillList" +
                "(id integer primary key autoincrement," +
                "location text," +
                "checkin text," +
                "customer_name TEXT," +
                "amount_customer TEXT ) ");

        String createPaidFoodHolder = ("CREATE TABLE PaidFood" +
                "(id integer primary key autoincrement," +
                "location text ," +
                "checkin text ," +
                " product_name TEXT," +
                " product_price INT ," +
                "product_amount INTEGER) ");

        MyDB.execSQL(createTableProduct);
        MyDB.execSQL(createTableOrdering);
        MyDB.execSQL(createTableTableNumber);
        MyDB.execSQL(createBillList);
        MyDB.execSQL(createPaidFoodHolder);



    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists Product");
        MyDB.execSQL("drop Table if exists Ordering");
        MyDB.execSQL("drop Table if exists Tablee");
        MyDB.execSQL("drop Table if exists BillList");
        MyDB.execSQL("drop Table if exists PaidFood");


    }
    public boolean insertTableeData(String location,String amount_customer, Boolean booked , String checkin , String customer_name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("location",location);
        contentValues.put("booked",booked);
        contentValues.put("amount_customer" , amount_customer);
        contentValues.put("customer_name",customer_name);
        contentValues.put("checkin" , checkin);
        long result = MyDB.insert("Tablee" , null,contentValues);
        if(result == -1) return false;
        else
            return true;

    }


    public boolean insertListBill(String location,String checkinTime, String customer_name , String amount_customer) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("location",location);
        contentValues.put("checkin", checkinTime);
        contentValues.put("customer_name",customer_name);
        contentValues.put("amount_customer" , amount_customer);
        long result = MyDB.insert("BillList" , null,contentValues);
        if(result == -1) return false;
        else
            return true;

    }

    public boolean insertPaidFood(String location,String checkin, String product_name ,  Integer product_price , Integer product_amount) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("location",location);
        contentValues.put("checkin", checkin);
        contentValues.put("product_name",product_name);
        contentValues.put("product_price" , product_price);
        contentValues.put("product_amount" , product_amount);

        long result = MyDB.insert("PaidFood" , null,contentValues);
        if(result == -1) return false;
        else
            return true;

    }
    public boolean updateTableeData(String location,String amount_customer, Boolean booked , String checkin , String customer_name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("location",location);
        contentValues.put("booked",booked);
        contentValues.put("amount_customer" , amount_customer);
        contentValues.put("customer_name",customer_name);
        contentValues.put("checkin" , checkin);
        Cursor cursor  = MyDB.rawQuery("Select * from Tablee where location = ?", new String[] {location});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.update("Tablee" , contentValues , "location=?", new String[] {location});
            if(result == -1) return false;
            else
                return true;
        }else return false;
    }
    public boolean updateBookedTable(String key, boolean newValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("booked", newValue);
        String selection = "location = ?";
        String[] selectionArgs = {key};
        int count = db.update(
                "tablee",
                values,
                selection,
                selectionArgs
        );
        db.close();
        if(count >0) return true;
        return false;
    }
    public boolean updateAmountOrderFood(String key, int newValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_amount", newValue);
        String selection = "table_number = ?";
        String[] selectionArgs = {key};
        int count = db.update(
                "Ordering",
                values,
                selection,
                selectionArgs
        );
        db.close();
        if(count >0) return true;
        return false;
    }
    public boolean isTableEmpty( String tableName) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT COUNT(*) FROM " + tableName, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }
    public Boolean deleteTableeData(String location ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor  = MyDB.rawQuery("Select * from Tablee where location = ?", new String[] {location});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.delete("Tablee"  , "Location=?", new String[] {location});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public boolean insertOrderingData(String tableNumber, String product_url, String product_name,int product_amount,int product_price) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("table_number",tableNumber);
        contentValues.put("product_url",product_url);
        contentValues.put("product_name",product_name);
        contentValues.put("product_amount",product_amount);
        contentValues.put("product_price",product_price);

        long result = MyDB.insert("Ordering" , null,contentValues);
        if(result == -1) return false;
        else
            return true;
    }

//    public boolean updateOrderingData(String tableNumber, String product_url, String product_name,int product_amount,int product_price) {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("table_number",tableNumber);
//        contentValues.put("product_url",product_url);
//        contentValues.put("product_name",product_name);
//        contentValues.put("product_amount",product_amount);
//        contentValues.put("product_price",product_price);
//        Cursor cursor  = MyDB.rawQuery("Select * from Ordering where table_number = ?", new String[] {tableNumber});
//        if(cursor.getCount() > 0 ) {
//            long result = MyDB.update("Ordering" , contentValues , "table_number=?", new String[] {tableNumber});
//            if(result == -1) return false;
//            else
//                return true;
//        }else return false;
//    }
@SuppressLint("Range")
public String getLastValue() {
    SQLiteDatabase db = this.getReadableDatabase();

    String result = null;

    // Sử dụng câu lệnh SELECT để lấy tất cả các giá trị trong bảng, sắp xếp theo thứ tự giảm dần của khóa chính, và chỉ lấy giá trị đầu tiên.
    String query = "SELECT * FROM Product ORDER BY product_id DESC LIMIT 1";
    Cursor cursor = db.rawQuery(query, null);

    if (cursor.moveToFirst()) {
        result = cursor.getString(cursor.getColumnIndex("product_id"));
    }

    cursor.close();
    db.close();

    return result;
}
    public boolean updateOrder(String tableNumber, String product_url, String product_name,int product_amount,int product_price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("table_number",tableNumber);
        contentValues.put("product_url",product_url);
        contentValues.put("product_name",product_name);
        contentValues.put("product_amount",product_amount);
        contentValues.put("product_price",product_price);
        int numRows = db.update("Ordering", contentValues, "table_number = ? AND product_name = ?", new String[] {tableNumber, product_name});
        db.close();
        return numRows > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng, ngược lại trả về false
    }
//    public Boolean deleteOrderingData(String tableNumber ) {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//
//
//        Cursor cursor  = MyDB.rawQuery("Select * from Ordering where tableNumber = ?", new String[] {tableNumber});
//        if(cursor.getCount() > 0 ) {
//            long result = MyDB.delete("Ordering"  , "tableNumber=?", new String[] {tableNumber});
//            if(result == -1) return false;
//            else
//                return true;
//        }else return false;
//
//    }
    public Boolean insertProductData(Integer product_id,String productImageUrl, String productName , String productPrice, String productType, String productDescripe ) {
          SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id" , product_id);
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
    public Boolean updateProductData(Integer product_id,String productImageUrl, String productName , String productPrice, String productType, String productDescripe ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id" , product_id);
        contentValues.put("product_url" , productImageUrl);
        contentValues.put("product_name" , productName);
        contentValues.put("product_price" , productPrice);
        contentValues.put("product_type" , productType);
        contentValues.put("product_descripe" , productDescripe);
        Cursor cursor  = MyDB.rawQuery("Select * from Product where product_id = ?", new String[] {String.valueOf(product_id)});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.update("Product" , contentValues , "product_id=?", new String[] {String.valueOf(product_id)});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public Boolean deleteProductData(int product_id ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor  = MyDB.rawQuery("Select * from Product where product_id = ?", new String[] {String.valueOf(product_id)});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.delete("Product"  , "product_id=?", new String[] {String.valueOf(product_id)});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public Cursor getProductData() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor  = MyDB.rawQuery("Select * from Product ",null    );
        return cursor;

    }

    public Cursor getListBills() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor  = MyDB.rawQuery("Select * from BillList ",null    );
        return cursor;

    }

    public Cursor getPaidFood() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor  = MyDB.rawQuery("Select * from PaidFood ",null    );
        return cursor;

    }
    public Cursor getOrdering() {
        SQLiteDatabase MyDB = this.getWritableDatabase();


        Cursor cursor  = MyDB.rawQuery("Select * from Ordering ",null    );
        return cursor;

    }
    public Cursor getTableeData() {
        SQLiteDatabase MyDB = this.getWritableDatabase();


        Cursor cursor  = MyDB.rawQuery("Select * from Tablee ",null    );
        return cursor;

    }
    public void deleteAllDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + "Product");
        String createTableProduct = ("CREATE TABLE Product" +
                "(product_id INTEGER primary key AUTOINCREMENT," +
                "product_url TEXT," +
                " product_name TEXT ," +
                " product_price INT , product_type TEXT," +
                " product_descripe TEXT)");
        db.execSQL(createTableProduct);
    }
    public boolean isValueExists( String tableName, String columnName, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(tableName, new String[] { columnName },
                columnName + "=?", new String[] { value }, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
    public boolean isDataExists(String tableNumber, String productName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Ordering WHERE table_number=? AND product_name=?", new String[]{tableNumber,productName});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}



    

