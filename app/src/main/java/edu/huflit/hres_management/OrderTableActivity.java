package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.huflit.hres_management.Adapter.TableOrderAdapter;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Fragment.BottomBarFragment;
import edu.huflit.hres_management.Model.TableOrder;

public class OrderTableActivity extends AppCompatActivity {
    ImageView imgvOrder;
    LinearLayout  lnInfoOrder;
    TextView tvNameOrder,tvAmountOrder,tvTimeOrder,tvNumberTableOrder;
    TableOrderAdapter tableOrderAdapter;
    RecyclerView mrcvOrder;
    ArrayList<TableOrder> tableOrderDataHolder = new ArrayList<>();
    DBHelper db = new DBHelper(this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_table); Fragment bottomBar = new BottomBarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bottom_bar, bottomBar).commit();


        imgvOrder = (ImageView) findViewById(R.id.imgv_order);
        lnInfoOrder = (LinearLayout) findViewById(R.id.ln_info_order);
        tvNameOrder = (TextView) findViewById(R.id.tv_name_customer_order);
        tvAmountOrder = (TextView) findViewById(R.id.tv_amount_people_order);
        tvTimeOrder = (TextView) findViewById(R.id.tv_time_order);
        tvNumberTableOrder = (TextView) findViewById(R.id.number_table_order);
        mrcvOrder = (RecyclerView) findViewById(R.id.rcviewOrder);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mrcvOrder.setLayoutManager(gridLayoutManager);



        Cursor cursor = db.getTableeData();
        while (cursor.moveToNext()) {
            boolean checkBooked = getBooleanValue(cursor, "booked");
            if (checkBooked == true) {
                TableOrder obj = new TableOrder(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), checkBooked);
                tableOrderDataHolder.add(obj);

            }
        }
        edu.huflit.hres_management.Adapter.TableOrderAdapter OrderAdapter  = new TableOrderAdapter(OrderTableActivity.this,tableOrderDataHolder);
        mrcvOrder.setAdapter(OrderAdapter);
        cursor.close();




    }
    public static boolean getBooleanValue(Cursor cursor, String columnName) {
        boolean value = false;

        final int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            final int rowValue = cursor.getInt(columnIndex);
            value = (rowValue != 0);
        }

        return value;
    }
}