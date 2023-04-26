package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class OrderFoodActivity extends AppCompatActivity {
    ListView lvOrder;
    Button btnDessert, btnDrinks,btnMainCourse,btnAppitizers;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        lvOrder = (ListView) findViewById(R.id.lvOrder);
        btnAppitizers = findViewById(R.id.btn_appetizer);
        btnDessert = findViewById(R.id.btnDessert);
        btnDrinks = findViewById(R.id.btnDrinks);
        btnMainCourse = findViewById(R.id.btnMainCourse);

    }
}