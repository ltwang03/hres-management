package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ListTypeFoodActivity extends AppCompatActivity {

    LinearLayout lnKhaiVi,lnMonchinh,lnDessert,lnDrinks;

    private RecyclerView rcvKhaivi,rcvMonchinh,rcvDessert,rcvDrinks;

    boolean rcvKVVisible = false;
    boolean rcvTMVisible = false;
    boolean rcvMCVisible = false;
    boolean rcvDVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_type_food);
//ds mon khai vi
        rcvKhaivi = findViewById(R.id.rcvKhaivi);
        edu.huflit.hres_management.KhaiviAdapter khaiviAdapter = new KhaiviAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        LinearLayoutManager linearLayoutManager_1 = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        LinearLayoutManager linearLayoutManager_2 = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        LinearLayoutManager linearLayoutManager_3 = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        rcvKhaivi.setLayoutManager(linearLayoutManager);

        khaiviAdapter.setData(getListKhaivi());
        rcvKhaivi.setAdapter(khaiviAdapter);

        lnKhaiVi=findViewById(R.id.lnKhaiVi);

        lnKhaiVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!rcvKVVisible){
                    rcvKhaivi.setVisibility(View.VISIBLE);
                    rcvKVVisible=true;
                }
                else{
                    rcvKhaivi.setVisibility(View.GONE);
                    rcvKVVisible=false;
                }
            }
        });
        //ds món chính
        rcvMonchinh = findViewById(R.id.rcvMonchinh);
        edu.huflit.hres_management.MonchinhAdapter monchinhAdapter = new MonchinhAdapter(this);
        rcvMonchinh.setLayoutManager(linearLayoutManager_1);
        monchinhAdapter.setData(getListMonchinh());
        rcvMonchinh.setAdapter(monchinhAdapter);

        lnMonchinh=findViewById(R.id.lnMonchinh);

        lnMonchinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!rcvMCVisible){
                    rcvMonchinh.setVisibility(View.VISIBLE);
                    rcvMCVisible=true;
                }
                else{
                    rcvMonchinh.setVisibility(View.GONE);
                    rcvMCVisible=false;
                }
            }
        });
//ds món tráng miệng
        rcvDessert = findViewById(R.id.rcvDessert);
        edu.huflit.hres_management.DessertAdapter dessertAdapter = new DessertAdapter(this);
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
        edu.huflit.hres_management.DrinksAdapter drinksAdapter = new DrinksAdapter(this);
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
    private List<Khaivi> getListKhaivi(){
        List<Khaivi> list = new ArrayList<>();
        list.add(new Khaivi(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Khaivi(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Khaivi(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));

        return list;
    }
    private List<Monchinh> getListMonchinh(){
        List<Monchinh> list = new ArrayList<>();
        list.add(new Monchinh(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Monchinh(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Monchinh(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));

        return list;
    }

    private List<Dessert> getListDessert(){
        List<Dessert> list = new ArrayList<>();
        list.add(new Dessert(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Dessert(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Dessert(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));

        return list;
    }

    private List<Drinks> getListDrinks(){
        List<Drinks> list = new ArrayList<>();
        list.add(new Drinks(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Drinks(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Drinks(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));

        return list;
    }
}