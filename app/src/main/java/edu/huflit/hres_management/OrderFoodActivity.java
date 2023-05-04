package edu.huflit.hres_management;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.huflit.hres_management.Adapter.AppetizerAdapter;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.Model.Dessert;
import edu.huflit.hres_management.Model.Drinks;
import edu.huflit.hres_management.Model.Maincourse;

public class OrderFoodActivity extends AppCompatActivity {
    RecyclerView lvOrder;
    TextView tvNameFood, tvAmountFood,tvTimeCheckinFood,tvTableNumberFood;
    SharedPreferences sharedPref;
    DBHelper db;
    ArrayList<Appetizer> appetizerDataHolder = new ArrayList<>();
    ArrayList<Dessert> dessertDataHolder  = new ArrayList<>();
    ArrayList<Drinks> drinkDataHolder  = new ArrayList<>();
    ArrayList<Maincourse> maincoursesDataHolder = new ArrayList<>();

    Button btnDessert, btnDrinks,btnMainCourse,btnAppitizers;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        db = new DBHelper(this);
        lvOrder = (RecyclerView) findViewById(R.id.lvOrder);
        btnAppitizers = findViewById(R.id.btn_appetizer);
        btnDessert = findViewById(R.id.btnDessert);
        btnDrinks = findViewById(R.id.btnDrinks);
        tvNameFood= findViewById(R.id.tv_nameCusFood);
        tvAmountFood = findViewById(R.id.tv_amountCusFood);
        tvTimeCheckinFood = findViewById(R.id.tv_timeCheckInFood);
        tvTableNumberFood = findViewById(R.id.tv_numberTableFood);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        btnMainCourse = findViewById(R.id.btnMainCourse);
        String nameCustomer = sharedPref.getString("customer_name", "");
        String amountCustomer = sharedPref.getString("amount_customer", "");
        String tableNumber = sharedPref.getString("table_number", "");
        String timeCheckinFood = sharedPref.getString("time_checkin", "");
        tvNameFood.setText("Tên KH : " +nameCustomer);
        tvAmountFood.setText("Số lượng  :"+amountCustomer);
        tvTimeCheckinFood.setText("Giờ vào : "+timeCheckinFood);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        LinearLayoutManager linearLayoutManager_1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        LinearLayoutManager linearLayoutManager_2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        LinearLayoutManager linearLayoutManager_3 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        tvTableNumberFood.setText(tableNumber);
        Cursor cursor = db.getProductData();
        while(cursor.moveToNext()) {
            if (cursor.getString(3).equals("Khai vị")) {
                Appetizer obj = new Appetizer(db.getProductData().getCount()+1,cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                appetizerDataHolder.add(obj);

            }lvOrder.setLayoutManager(linearLayoutManager);
            AppetizerAdapter appetizerAdapter = new AppetizerAdapter(this,appetizerDataHolder);
            lvOrder.setAdapter(appetizerAdapter);
        }
        btnAppitizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Log.e(TAG, "onCreate: " +"Name :" +nameCustomer + "Amount :"+ amountCustomer +"Table Number:" + tableNumber );



    }
}