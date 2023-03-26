package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

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

    private RecyclerView rcvAppetizer,rcvMaincourse,rcvDessert,rcvDrinks;

    boolean rcvKVVisible = false;
    boolean rcvTMVisible = false;
    boolean rcvMCVisible = false;
    boolean rcvDVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_type_food);
//ds mon khai vi
        rcvAppetizer = findViewById(R.id.rcvAppetizer);
        edu.huflit.hres_management.Adapter.AppetizerAdapter appetizerAdapter = new AppetizerAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        LinearLayoutManager linearLayoutManager_1 = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        LinearLayoutManager linearLayoutManager_2 = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        LinearLayoutManager linearLayoutManager_3 = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        rcvAppetizer.setLayoutManager(linearLayoutManager);

        appetizerAdapter.setData(getListAppetizer());
        rcvAppetizer.setAdapter(appetizerAdapter);

        lnAppetizer=findViewById(R.id.lnAppetizer);

        lnAppetizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!rcvKVVisible){
                    rcvAppetizer.setVisibility(View.VISIBLE);
                    rcvKVVisible=true;
                }
                else{
                    rcvAppetizer.setVisibility(View.GONE);
                    rcvKVVisible=false;
                }
            }
        });
        //ds món chính
        rcvMaincourse = findViewById(R.id.rcvMaincourse);
        MaincourseAdapter maincourseAdapter = new MaincourseAdapter(this);
        rcvMaincourse.setLayoutManager(linearLayoutManager_1);
        maincourseAdapter.setData(getListMaincourse());
        rcvMaincourse.setAdapter(maincourseAdapter);

        lnMaincourse=findViewById(R.id.lnMaincourse);

        lnMaincourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!rcvMCVisible){
                    rcvMaincourse.setVisibility(View.VISIBLE);
                    rcvMCVisible=true;
                }
                else{
                    rcvMaincourse.setVisibility(View.GONE);
                    rcvMCVisible=false;
                }
            }
        });
//ds món tráng miệng
        rcvDessert = findViewById(R.id.rcvDessert);
        edu.huflit.hres_management.Adapter.DessertAdapter dessertAdapter = new DessertAdapter(this);
        rcvDessert.setLayoutManager(linearLayoutManager_2);
        dessertAdapter.setData(getListDessert());
        rcvDessert.setAdapter(dessertAdapter);

        lnDessert=findViewById(R.id.lnDessert);

        lnDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!rcvTMVisible){
                    rcvDessert.setVisibility(View.VISIBLE);
                    rcvTMVisible=true;
                }
                else{
                    rcvDessert.setVisibility(View.GONE);
                    rcvTMVisible=false;
                }
            }
        });
//ds món nước
        rcvDrinks = findViewById(R.id.rcvDrinks);
        edu.huflit.hres_management.Adapter.DrinksAdapter drinksAdapter = new DrinksAdapter(this);
        rcvDrinks.setLayoutManager(linearLayoutManager_3);
        drinksAdapter.setData(getListDrinks());
        rcvDrinks.setAdapter(drinksAdapter);

        lnDrinks=findViewById(R.id.lnDrinks);

        lnDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!rcvDVisible){
                    rcvDrinks.setVisibility(View.VISIBLE);
                    rcvDVisible=true;
                }
                else{
                    rcvDrinks.setVisibility(View.GONE);
                    rcvDVisible=false;
                }
            }
        });

    }
    private List<Appetizer> getListAppetizer(){
        List<Appetizer> list = new ArrayList<>();
        list.add(new Appetizer(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Appetizer(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Appetizer(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Appetizer(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Appetizer(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Appetizer(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));


        return list;
    }
    private List<Maincourse> getListMaincourse(){
        List<Maincourse> list = new ArrayList<>();
        list.add(new Maincourse(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Maincourse(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Maincourse(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));

        return list;
    }

    private List<Dessert> getListDessert(){
        List<Dessert> list = new ArrayList<>();
        list.add(new Dessert(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Dessert(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Dessert(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));

        list.add(new Dessert(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Dessert(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Dessert(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));

        return list;
    }

    private List<Drinks> getListDrinks(){
        List<Drinks> list = new ArrayList<>();
        list.add(new Drinks(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Drinks(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Drinks(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Drinks(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Drinks(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Drinks(R.drawable.imgdessert1,"Món ăn 1", "Mô tả 1", 50000));

        return list;
    }
}