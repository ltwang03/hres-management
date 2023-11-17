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
    ArrayList<FoodBill> listFoodBill = new ArrayList<>();
    ArrayList<Bill> listBill = new ArrayList<>();
    DBHelper db = new DBHelper(this);
    ArrayList<ListBill> listBills = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        Fragment bottomBar = new BottomBarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bottom_bar, bottomBar).commit();
        Cursor c1 = db.getListBills();
        while (c1.moveToNext()) {
            listBill.add(new Bill(c1.getString(1), c1.getString(2),c1.getString(3),c1.getString(4)));
        }
        c1.close();
        billAdapter = new BillAdapter(ListBillActivity.this,listBill,listFoodBill);
        LinearLayoutManager linearLayoutManager_Bill = new LinearLayoutManager(ListBillActivity.this, RecyclerView.VERTICAL, false);
        rcvListBill = findViewById(R.id.rcv_listBill);
        rcvListBill.setLayoutManager(linearLayoutManager_Bill);
        rcvListBill.setAdapter(billAdapter);
    }


}




