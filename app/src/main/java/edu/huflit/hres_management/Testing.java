package edu.huflit.hres_management;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.huflit.hres_management.Database.DBHelper;


public class Testing extends AppCompatActivity {
    Button btntest , btnCheckout , btnPushData;
    String tableNumber = "2";
    DBHelper db;
    String formattedTime, formattedTime1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        btntest = (Button) findViewById(R.id.btntesting);
        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        btnPushData = (Button) findViewById(R.id.btnPushData);

        db = new DBHelper(this);


        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("table_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("table_" + 1 + "_check_in_time", System.currentTimeMillis());
                editor.apply();

                long checkInTime = sharedPreferences.getLong("table_" + 1 + "_check_in_time", 0);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                 formattedTime = sdf.format(new Date(checkInTime));

                Log.e(TAG, "onCreate: " + formattedTime);
            }
        });
        btnPushData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertOrderingData = db.insertOrderingData(tableNumber,formattedTime,formattedTime1);
                if(checkInsertOrderingData== true) {
                    Toast.makeText(Testing.this, "New entry inserted", Toast.LENGTH_SHORT ).show();
                }else {
                    Toast.makeText(Testing.this, "New entry inserted Failed", Toast.LENGTH_SHORT ).show();
                }
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("table_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putLong("table_" + 1 + "_check_out_time", System.currentTimeMillis());
                editor1.apply();
                long checkOutTime = sharedPreferences.getLong("table_" + 1 + "_check_out_time", 0);
                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                 formattedTime1 = sdf1.format(new Date(checkOutTime));
                Log.e(TAG, "onCreate: " + formattedTime1 );
            }
        });

    }
}