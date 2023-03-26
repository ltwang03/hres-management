package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {
    LinearLayout linear_staff , linear_customer , linear_orderList, linear_booking, linear_foodList,linear_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        linear_add = (LinearLayout) findViewById(R.id.linear_add);
        linear_customer = (LinearLayout) findViewById(R.id.linear_customer);
        linear_orderList = (LinearLayout) findViewById(R.id.linear_order);
        linear_booking = (LinearLayout) findViewById(R.id.linear_booking);
        linear_foodList = (LinearLayout) findViewById(R.id.linear_foodList);
        linear_staff = (LinearLayout) findViewById(R.id.linear_staff);
        linear_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,ListTypeFoodActivity.class);
                startActivity(i);
            }
        });
        mstaffHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,ListStaffActivity.class);
                startActivity(i);
            }
        });
        mcustomerHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,ListCustomerActivity.class);
                startActivity(i);
            }
        });

        linear_foodList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Home.this, ListTypeFoodActivity.class);
                startActivity(i1);
            }
        });
    }
}