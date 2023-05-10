package edu.huflit.hres_management;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.huflit.hres_management.Adapter.OrderAdapter.OrderAppetizerAdapter;
import edu.huflit.hres_management.Adapter.SingleBillAdapter;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.Model.FoodBill;

public class SingleBill extends AppCompatActivity {
    DBHelper db;
    ArrayList<FoodBill> arrayFoodBill = new ArrayList<>();
    TextView tvNameCustomer,tvTableNumber,tvTimeCheckin,tvAmountCustomer,tvTotalMoney;
    RecyclerView rcvBill;
    int totalprice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_bill);
        tvNameCustomer = findViewById(R.id.tv_nameCusBill);
        tvTableNumber = findViewById(R.id.tv_numberTableBill);
        tvTimeCheckin = findViewById(R.id.tv_timeCheckInBill);
        tvAmountCustomer= findViewById(R.id.tv_amountCusBill);
        rcvBill = findViewById(R.id.lvBill);
        tvTotalMoney = findViewById(R.id.tvTotalPrice);


        LinearLayoutManager ln1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String nameCustomer = sharedPref.getString("customer_name", "");
        String amountCustomer = sharedPref.getString("amount_customer", "");
        String tableNumber = sharedPref.getString("table_number", "");
        String timeCheckinFood = sharedPref.getString("time_checkin", "");
        tvNameCustomer.setText("Khách hàng : "+nameCustomer);
        tvAmountCustomer.setText("Số lượng : " +amountCustomer);
        tvTimeCheckin.setText("Giờ vào : "+timeCheckinFood);
        tvTableNumber.setText(tableNumber);
        db = new DBHelper(SingleBill.this);
        Cursor cursor = db.getOrdering();
        Log.e(TAG, "onCreate: "+tableNumber );
        while (cursor.moveToNext()) {
            if(cursor.getInt(0)  == Integer.parseInt(tableNumber) && cursor.getInt(3) !=0) {
                Log.e(TAG, "onCreate: 1" + "ok" );
                FoodBill obj = new FoodBill(cursor.getInt(4),cursor.getString(2), cursor.getInt(3));
                arrayFoodBill.add(obj);
                totalprice+=(cursor.getInt(4) * cursor.getInt(3));
            }

            rcvBill.setLayoutManager(ln1);
            SingleBillAdapter sgba = new SingleBillAdapter(this,arrayFoodBill);
            rcvBill.setAdapter(sgba);
        }
        DecimalFormat formater = new DecimalFormat("#,### đ");
        String formatedStr = formater.format(totalprice);
        tvTotalMoney.setText(("Total : "+formatedStr));

    }

}