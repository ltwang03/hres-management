package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Adapter.BillAdapter;
import edu.huflit.hres_management.Fragment.BottomBarFragment;
import edu.huflit.hres_management.Model.Bill;
import edu.huflit.hres_management.Model.FoodBill;

public class ListBillActivity extends AppCompatActivity {

    private RecyclerView rcvListBill;
    private BillAdapter billAdapter;
    List<FoodBill> listFoodBill;
    List<Bill> listBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill); Fragment bottomBar = new BottomBarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bottom_bar, bottomBar).commit();

//        billAdapter = new BillAdapter(ListBillActivity.this,get());
        LinearLayoutManager linearLayoutManager_Bill=new LinearLayoutManager(ListBillActivity.this,RecyclerView.VERTICAL,false);
        rcvListBill=findViewById(R.id.rcv_listBill);
        rcvListBill.setLayoutManager(linearLayoutManager_Bill);
        rcvListBill.setAdapter(billAdapter);
    }

//    private List<Bill> getBillList(){
//        listFoodBill=new ArrayList<>();
//        listFoodBill.add(0,new FoodBill(1,"ga",5,100));
//        listFoodBill.add(1,new FoodBill(3,"ga",5,100));
//
//        listBill = new ArrayList<>();
//        listBill.add(0,new Bill("mydieu","12:50","250",listFoodBill));
//        listBill.add(1,new Bill("mydieu","12:50","250",listFoodBill));
//        listBill.add(2,new Bill("mydieu","12:50","250",listFoodBill));
//        return listBill;
    }
