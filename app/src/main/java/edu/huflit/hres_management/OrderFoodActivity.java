package edu.huflit.hres_management;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.huflit.hres_management.Adapter.FoodAdapter.MaincourseAdapter;
import edu.huflit.hres_management.Adapter.OrderAdapter.OrderAppetizerAdapter;
import edu.huflit.hres_management.Adapter.OrderAdapter.OrderDessertAdapter;
import edu.huflit.hres_management.Adapter.OrderAdapter.OrderDrinksAdapter;
import edu.huflit.hres_management.Adapter.OrderAdapter.OrderMainCourseAdapter;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.Model.Dessert;
import edu.huflit.hres_management.Model.Drinks;
import edu.huflit.hres_management.Model.Maincourse;

public class OrderFoodActivity extends AppCompatActivity {
    RecyclerView lvOrder;
    TextView tvNameFood, tvAmountFood,tvTimeCheckinFood,tvTableNumberFood;
    EditText edtAmountFood;
    SharedPreferences sharedPref;
    DBHelper db = new DBHelper(this);
    ArrayList<Appetizer> appetizerDataHolder = new ArrayList<>();
    ArrayList<Dessert> dessertDataHolder  = new ArrayList<>();
    ArrayList<Drinks> drinkDataHolder  = new ArrayList<>();
    ArrayList<Maincourse> maincoursesDataHolder = new ArrayList<>();

    Button btnDessert, btnDrinks,btnMainCourse,btnAppitizers , btnCancel , btnSend;
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
        btnCancel = findViewById(R.id.btn_cancelOrder);
        btnSend = findViewById(R.id.btn_doneOrder);
        edtAmountFood = findViewById(R.id.edt_amountFood);

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
        tvTableNumberFood.setText(tableNumber);


        btnAppitizers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvOrder.setAdapter(null);
                appetizerDataHolder.clear();
                Cursor cursor =  db.getProductData();
                while(cursor.moveToNext()) {
                    if (cursor.getString(4).equals("Khai vị")) {

                        Appetizer obj = new Appetizer(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getString(3));
                        appetizerDataHolder.add(obj);
                    }
                    lvOrder.setLayoutManager(linearLayoutManager);
                    OrderAppetizerAdapter orderappetizerAdapter = new OrderAppetizerAdapter(OrderFoodActivity.this,appetizerDataHolder);
                    lvOrder.setAdapter(orderappetizerAdapter);
                }
            }
        });
        btnMainCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvOrder.setAdapter(null);
                maincoursesDataHolder.clear();
                Cursor cursor =  db.getProductData();
                while(cursor.moveToNext()) {
                    if (cursor.getString(4).equals("Món chính")) {
                        Maincourse obj = new Maincourse(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getString(3));
                        maincoursesDataHolder.add(obj);
                    }
                    lvOrder.setLayoutManager(linearLayoutManager);
                    OrderMainCourseAdapter orderMaincourseAdapter = new OrderMainCourseAdapter(OrderFoodActivity.this,maincoursesDataHolder);
                    lvOrder.setAdapter(orderMaincourseAdapter);
                }
            }
        });
        btnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvOrder.setAdapter(null);
                dessertDataHolder.clear();
                Cursor cursor =  db.getProductData();
                while(cursor.moveToNext()) {
                    if (cursor.getString(4).equals("Tráng miệng")) {
                        Dessert obj = new Dessert(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getString(3));
                        dessertDataHolder.add(obj);
                    }
                    lvOrder.setLayoutManager(linearLayoutManager);
                    OrderDessertAdapter orderDessertAdapter = new OrderDessertAdapter(OrderFoodActivity.this,dessertDataHolder);
                    lvOrder.setAdapter(orderDessertAdapter);
                }
            }

        });
        btnDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvOrder.setAdapter(null);
                drinkDataHolder.clear();
                Cursor cursor =  db.getProductData();
                while(cursor.moveToNext()) {
                    if (cursor.getString(4).equals("Nước giải khát")) {
                        Drinks obj = new Drinks(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getString(3));
                        drinkDataHolder.add(obj);
                    }
                    lvOrder.setLayoutManager(linearLayoutManager);
                    OrderDrinksAdapter orderDrinksAdapter = new OrderDrinksAdapter(OrderFoodActivity.this,drinkDataHolder);
                    lvOrder.setAdapter(orderDrinksAdapter);
                }
            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderFoodActivity.this,OrderTableActivity.class);
                startActivity(i);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}