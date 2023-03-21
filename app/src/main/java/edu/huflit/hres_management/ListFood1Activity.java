package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListFood1Activity extends AppCompatActivity {

    private RecyclerView rcvFood1;
    private Food1Adapter food1Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food1);

        rcvFood1 = findViewById(R.id.rcvFood1);
        food1Adapter = new Food1Adapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvFood1.setLayoutManager(linearLayoutManager);
        food1Adapter.setData(getListFood1());
        rcvFood1.setAdapter(food1Adapter);
    }
    private List<Food1> getListFood1(){
        List<Food1> list = new ArrayList<>();
        list.add(new Food1(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Food1(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Food1(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));

        list.add(new Food1(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Food1(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Food1(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));

        list.add(new Food1(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Food1(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));
        list.add(new Food1(R.drawable.imgkhaivi1,"Món ăn 1", "Mô tả 1", 50000));

        return list;
    }
}