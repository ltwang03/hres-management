package edu.huflit.hres_management;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.huflit.hres_management.Adapter.OrderAdapter.OrderAppetizerAdapter;
import edu.huflit.hres_management.Adapter.SingleBillAdapter;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.Model.FoodBill;
import edu.huflit.hres_management.Model.ListBill;

public class SingleBill extends AppCompatActivity {
    DBHelper db;
    ArrayList<FoodBill> arrayFoodBill = new ArrayList<>();

    ArrayList<ListBill> arrayListBill = new ArrayList<>();
    Button btnPrintBill,btnPayment;
    TextView tvNameCustomer,tvTableNumber,tvTimeCheckin,tvAmountCustomer,tvTotalMoney;
    RecyclerView rcvBill;
    int totalprice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_bill);


//        zalo init


        tvNameCustomer = findViewById(R.id.tv_nameCusBill);
        tvTableNumber = findViewById(R.id.tv_numberTableBill);
        tvTimeCheckin = findViewById(R.id.tv_timeCheckInBill);
        tvAmountCustomer= findViewById(R.id.tv_amountCusBill);
        rcvBill = findViewById(R.id.lvBill);
        tvTotalMoney = findViewById(R.id.tvTotalPrice);
        btnPrintBill = findViewById(R.id.btn_printBill);
        btnPayment = findViewById(R.id.btn_cancelBill);



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
            if(cursor.getInt(  1)  == Integer.parseInt(tableNumber) && cursor.getInt(4) !=0) {
                FoodBill obj = new FoodBill(cursor.getInt(5),cursor.getString(3), cursor.getInt(4));
                arrayFoodBill.add(obj);
                totalprice+=(cursor.getInt(5) * cursor.getInt(4));
            }

            rcvBill.setLayoutManager(ln1);
            SingleBillAdapter sgba = new SingleBillAdapter(this,arrayFoodBill);
            rcvBill.setAdapter(sgba);
        }
        DecimalFormat formater = new DecimalFormat("#,### đ");
        String formatedStr = formater.format(totalprice);
        tvTotalMoney.setText(("Total : "+formatedStr));
        btnPrintBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                db.insertListBill(tableNumber,timeCheckinFood,nameCustomer,amountCustomer);

        Cursor cursor3 = db.getOrdering();
                while (cursor3.moveToNext()) {

                    Log.d(TAG, "onClick" + cursor3.getString(1) + tableNumber);

                    if(cursor3.getString(1).equals(tableNumber)  && cursor3.getString(4) != "0") {

                        db.insertPaidFood(tableNumber,timeCheckinFood,cursor3.getString(3),cursor3.getInt(5),cursor3.getInt(4));
                    }
                }


                Cursor cursor1 = db.getTableeData();
                while (cursor1.moveToNext()) {
                    if(cursor1.getString(0).equals(tableNumber)) {
                        db.updateBookedTable(tableNumber,false);
                    }
                }
                cursor1.close();
                Cursor cursor2 = db.getOrdering();
                while(cursor2.moveToNext()) {
                    if(cursor2.getString(1).equals(tableNumber)){
                        db.updateAmountOrderFood(tableNumber,0);
                    }
                }
                Intent i = new Intent(SingleBill.this,OrderTableActivity.class);
                startActivity(i);
                finish();
            }


        });


    }
}