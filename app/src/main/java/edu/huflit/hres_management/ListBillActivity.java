package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Adapter.BillAdapter;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Fragment.BottomBarFragment;
import edu.huflit.hres_management.Model.Bill;
import edu.huflit.hres_management.Model.FoodBill;
import edu.huflit.hres_management.Model.ListBill;

public class ListBillActivity extends AppCompatActivity {

    private RecyclerView rcvListBill;
    private BillAdapter billAdapter;
    List<FoodBill> listFoodBill;
    List<Bill> listBill;
    DBHelper db = new DBHelper(this);
    ArrayList<ListBill> listBills = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        Fragment bottomBar = new BottomBarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bottom_bar, bottomBar).commit();

//        billAdapter = new BillAdapter(ListBillActivity.this,getBillList());
        LinearLayoutManager linearLayoutManager_Bill = new LinearLayoutManager(ListBillActivity.this, RecyclerView.VERTICAL, false);
        rcvListBill = findViewById(R.id.rcv_listBill);
        rcvListBill.setLayoutManager(linearLayoutManager_Bill);
        rcvListBill.setAdapter(billAdapter);
    }

    private List<Bill> getBillList() {
//        listFoodBill=new ArrayList<>();
//        listFoodBill.add(0,new FoodBill(10000,"ga",5));
//        listFoodBill.add(1,new FoodBill(30000,"ga",5));
//
//        listBill = new ArrayList<>();
//        listBill.add(0,new Bill("mydieu","12:50","250",listFoodBill));
//        listBill.add(1,new Bill("mydieu","12:50","250",listFoodBill));
//        listBill.add(2,new Bill("mydieu","12:50","250",listFoodBill));
//        return listBill;
//    }}

        Cursor cursor1 = db.getListBills();
        Cursor cursor2 = db.getPaidFood();

        while (cursor1.moveToNext()) {
           if((cursor1.getString(1).equals(cursor2.getString(1)) && cursor1.getString(2).equals(cursor2.getString(2)))
            {




            }
        }
    }
}