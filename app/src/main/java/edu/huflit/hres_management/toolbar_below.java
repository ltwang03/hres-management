package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class toolbar_below extends AppCompatActivity {
ImageView icHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_below);
        icHome = (ImageView) findViewById(R.id.ic_home);
        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(toolbar_below.this,Home.class);
                startActivity(i);
            }
        });
    }
}