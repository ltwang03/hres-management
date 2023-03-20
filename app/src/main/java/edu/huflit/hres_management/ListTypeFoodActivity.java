package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ListTypeFoodActivity extends AppCompatActivity {

    private RecyclerView rcyTypeFood;
    private TypeFoodAdapter typeFoodAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_type_food);
        rcyTypeFood = findViewById(R.id.rcy_typefood);
        typeFoodAdapter = new TypeFoodAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcyTypeFood.setLayoutManager(linearLayoutManager);
        typeFoodAdapter.setData(getListTYpeFood());
        rcyTypeFood.setAdapter(typeFoodAdapter);
    }
    private List<TypeFood> getListTYpeFood(){
        List<TypeFood> list = new ArrayList<>();
        list.add(new TypeFood(R.drawable.khaivi,"Khai vị"));
        list.add(new TypeFood(R.drawable.monchinh,"Món chính"));
        list.add(new TypeFood(R.drawable.trangmieng,"Tráng miệng"));
        list.add(new TypeFood(R.drawable.drinks,"Thức uống"));

        list.add(new TypeFood(R.drawable.khaivi,"Khai vị"));
        list.add(new TypeFood(R.drawable.monchinh,"Món chính"));
        list.add(new TypeFood(R.drawable.trangmieng,"Tráng miệng"));
        list.add(new TypeFood(R.drawable.drinks,"Thức uống"));
        return list;
    }
}