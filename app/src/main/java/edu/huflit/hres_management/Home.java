package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {
    LinearLayout mfoodHome,mstaffHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mfoodHome = (LinearLayout) findViewById(R.id.food_home);
        mstaffHome = (LinearLayout) findViewById(R.id.staff_home);

        mfoodHome.setOnClickListener(new View.OnClickListener() {
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
    }
}