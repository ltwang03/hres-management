package edu.huflit.hres_management;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Fragment.BottomBarFragment;
import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.Adapter.AppetizerAdapter;
import edu.huflit.hres_management.Model.Maincourse;
import edu.huflit.hres_management.Adapter.MaincourseAdapter;
import edu.huflit.hres_management.Model.Dessert;
import edu.huflit.hres_management.Adapter.DessertAdapter;
import edu.huflit.hres_management.Model.Drinks;
import edu.huflit.hres_management.Adapter.DrinksAdapter;

public class ListTypeFoodActivity extends AppCompatActivity {

    LinearLayout lnAppetizer,lnMaincourse,lnDessert,lnDrinks;
    FloatingActionButton add_food;
        DBHelper db;
    ArrayList<Appetizer> appetizerDataHolder = new ArrayList<>();
    ArrayList<Dessert> dessertDataHolder  = new ArrayList<>();
    ArrayList<Drinks> drinkDataHolder  = new ArrayList<>();
    ArrayList<Maincourse> maincoursesDataHolder = new ArrayList<>();
    private RecyclerView rcvAppetizer,rcvMaincourse,rcvDessert,rcvDrinks;
    boolean rcvKVVisible = false;
    boolean rcvTMVisible = false;
    boolean rcvMCVisible = false;
    boolean rcvDVisible = false;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_type_food);
        Fragment bottomBar = new BottomBarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bottom_bar, bottomBar).commit();

        add_food = (FloatingActionButton) findViewById(R.id.add_food);
        add_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getRole = sharedPreferences.getString("role","");
                if(Objects.equals(getRole,"staff")) {
                    Toast.makeText(ListTypeFoodActivity.this, "Bạn không có quyền để sử dụng chức năng này!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(ListTypeFoodActivity.this, AddProductsAcitivity.class);
                startActivity(i);

            }
        });

        db = new DBHelper(this);
        Cursor cursor = db.getProductData();
        while (cursor.moveToNext()) {
            Log.e(TAG, "onCreate: " + cursor.getString(0) + " / " + cursor.getString(1) + " / " + cursor.getString(4) + " / " + cursor.getString(3) + " / " + cursor.getString(2));
            if (cursor.getString(3).equals("Khai vị")) {
                Appetizer obj = new Appetizer(cursor.getString(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getString(2));
                appetizerDataHolder.add(obj);

            }
            if (cursor.getString(3).equals("Món chính")) {
                Maincourse obj1 = new Maincourse(cursor.getString(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getString(2));
                maincoursesDataHolder.add(obj1);

            }
            if (cursor.getString(3).equals("Nước giải khát")) {
                Drinks obj2 = new Drinks(cursor.getString(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getString(2));

                drinkDataHolder.add(obj2);

            }
            if (cursor.getString(3).equals("Tráng miệng")) {
                Dessert obj3 = new Dessert(cursor.getString(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), cursor.getString(2));
                dessertDataHolder.add(obj3);

            }

//


//ds mon khai vi
            rcvAppetizer = findViewById(R.id.rcvAppetizer);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            LinearLayoutManager linearLayoutManager_1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            LinearLayoutManager linearLayoutManager_2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            LinearLayoutManager linearLayoutManager_3 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

            rcvAppetizer.setLayoutManager(linearLayoutManager);

            edu.huflit.hres_management.Adapter.AppetizerAdapter appetizerAdapter = new AppetizerAdapter(appetizerDataHolder);
            rcvAppetizer.setAdapter(appetizerAdapter);

            lnAppetizer = findViewById(R.id.lnAppetizer);

            lnAppetizer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!rcvKVVisible) {
                        rcvAppetizer.setVisibility(View.VISIBLE);
                        rcvKVVisible = true;
                    } else {
                        rcvAppetizer.setVisibility(View.GONE);
                        rcvKVVisible = false;
                    }
                }
            });
            //ds món chính
            rcvMaincourse = findViewById(R.id.rcvMaincourse);
            MaincourseAdapter maincourseAdapter = new MaincourseAdapter(maincoursesDataHolder);
            rcvMaincourse.setLayoutManager(linearLayoutManager_1);

            rcvMaincourse.setAdapter(maincourseAdapter);

            lnMaincourse = findViewById(R.id.lnMaincourse);
            add_food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i1 = new Intent(ListTypeFoodActivity.this, AddProductsAcitivity.class);
                    startActivity(i1);
                }
            });
            lnMaincourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!rcvMCVisible) {
                        rcvMaincourse.setVisibility(View.VISIBLE);
                        rcvMCVisible = true;
                    } else {
                        rcvMaincourse.setVisibility(View.GONE);
                        rcvMCVisible = false;
                    }
                }
            });
//ds món tráng miệng
            rcvDessert = findViewById(R.id.rcvDessert);
            edu.huflit.hres_management.Adapter.DessertAdapter dessertAdapter = new DessertAdapter(dessertDataHolder);
            rcvDessert.setLayoutManager(linearLayoutManager_2);
            rcvDessert.setAdapter(dessertAdapter);

            lnDessert = findViewById(R.id.lnDessert);

            lnDessert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!rcvTMVisible) {
                        rcvDessert.setVisibility(View.VISIBLE);
                        rcvTMVisible = true;
                    } else {
                        rcvDessert.setVisibility(View.GONE);
                        rcvTMVisible = false;
                    }
                }
            });
//ds món nước
            rcvDrinks = findViewById(R.id.rcvDrinks);
            edu.huflit.hres_management.Adapter.DrinksAdapter drinksAdapter = new DrinksAdapter(drinkDataHolder);
            rcvDrinks.setLayoutManager(linearLayoutManager_3);

            rcvDrinks.setAdapter(drinksAdapter);

            lnDrinks = findViewById(R.id.lnDrinks);

            lnDrinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!rcvDVisible) {
                        rcvDrinks.setVisibility(View.VISIBLE);
                        rcvDVisible = true;
                    } else {
                        rcvDrinks.setVisibility(View.GONE);
                        rcvDVisible = false;
                    }
                }
            });

        }
    }
}