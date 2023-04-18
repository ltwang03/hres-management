package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.huflit.hres_management.Database.DBHelper;

public class booking extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        db = new DBHelper(this);

    }
}