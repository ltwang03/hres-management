package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import edu.huflit.hres_management.Fragment.BottomBarFragment;

public class Home extends AppCompatActivity {
    LinearLayout linear_staff , linear_customer , linear_orderList, linear_booking, linear_foodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Fragment bottomBar = new BottomBarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bottom_bar, bottomBar).commit();

        linear_customer = (LinearLayout) findViewById(R.id.linear_customer);
        linear_orderList = (LinearLayout) findViewById(R.id.linear_order);
        linear_booking = (LinearLayout) findViewById(R.id.linear_booking);
        linear_foodList = (LinearLayout) findViewById(R.id.linear_foodList);
        linear_staff = (LinearLayout) findViewById(R.id.linear_staff);

        linear_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,ListStaffActivity.class);
                startActivity(i);
            }
        });
        linear_customer.setOnClickListener(new View.OnClickListener() {
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
        linear_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,BookingTableActivity.class);
                startActivity(i);
            }
        });
        linear_orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,OrderTableActivity.class);
                startActivity(i);
            }
        });
    }
}