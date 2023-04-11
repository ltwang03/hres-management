package edu.huflit.hres_management;

import static edu.huflit.hres_management.R.menu.popup_booking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.huflit.hres_management.Adapter.TableBookingAdapter;
import edu.huflit.hres_management.Model.TableBooking;

public class BookingTableActivity extends AppCompatActivity {

    private RecyclerView mrcvBooking;
    private TableBookingAdapter mTableBookingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_table);
        //View view1 = getLayoutInflater().inflate(R.layout.dialog_form_booking,null);
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