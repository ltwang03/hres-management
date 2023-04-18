package edu.huflit.hres_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Adapter.TableBookingAdapter;
import edu.huflit.hres_management.Fragment.BottomBarFragment;
import edu.huflit.hres_management.Model.TableBooking;

public class BookingTableActivity extends AppCompatActivity {

    private RecyclerView mrcvBooking;
    private TableBookingAdapter mTableBookingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_table);
        Fragment bottomBar = new BottomBarFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bottom_bar, bottomBar).commit();

        mrcvBooking = findViewById(R.id.rcvBooking);
        mTableBookingAdapter = new TableBookingAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        mrcvBooking.setLayoutManager(gridLayoutManager);
        mTableBookingAdapter.setData(getListBooking());
        mrcvBooking.setAdapter(mTableBookingAdapter);

    }
    private List<TableBooking> getListBooking(){
        List<TableBooking> list = new ArrayList<>();
        list.add(new TableBooking(1,10,10 + "h"));
        list.add(new TableBooking(2,10,10 + "h"));
        list.add(new TableBooking(3,10,10 + "h"));

        list.add(new TableBooking(1,10,10 + "h"));
        list.add(new TableBooking(2,10,10 + "h"));
        list.add(new TableBooking(3,10,10 + "h"));

        list.add(new TableBooking(1,10,10 + "h"));
        list.add(new TableBooking(2,10,10 + "h"));
        list.add(new TableBooking(3,10,10 + "h"));

        list.add(new TableBooking(1,10,10 + "h"));
        list.add(new TableBooking(2,10,10 + "h"));
        list.add(new TableBooking(3,10,10 + "h"));

        list.add(new TableBooking(1,10,10 + "h"));
        list.add(new TableBooking(2,10,10 + "h"));
        list.add(new TableBooking(3,10,10 + "h"));

        list.add(new TableBooking(1,10,10 + "h"));
        list.add(new TableBooking(2,10,10 + "h"));
        list.add(new TableBooking(3,10,10 + "h"));

        list.add(new TableBooking(1,10,10 + "h"));
        list.add(new TableBooking(2,10,10 + "h"));

        return list;
    }
}