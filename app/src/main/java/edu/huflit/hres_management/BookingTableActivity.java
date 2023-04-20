package edu.huflit.hres_management;

import static android.content.ContentValues.TAG;
import static edu.huflit.hres_management.Adapter.TableBookingAdapter.getBooleanValue;
import static edu.huflit.hres_management.R.menu.popup_booking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.huflit.hres_management.Adapter.TableBookingAdapter;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.TableBooking;

public class BookingTableActivity extends AppCompatActivity {

    private RecyclerView mrcvBooking;
    TextView number_table;
    DBHelper db;


    boolean setVisibility = false;
    TextView tvName, tvAmount, tvTime, tvNumberTable;
    Dialog dialog;

    ArrayList<TableBooking> tableBookingDataHolder = new ArrayList<>();


    private TableBookingAdapter mTableBookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(BookingTableActivity.this);
        setContentView(R.layout.activity_booking_table);
        mrcvBooking = findViewById(R.id.rcvBooking);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mrcvBooking.setLayoutManager(gridLayoutManager);
        tvAmount = (TextView) findViewById(R.id.tv_amount_people);
        tvNumberTable = (TextView) findViewById(R.id.number_table);
        tvName = (TextView) findViewById(R.id.tv_name_booking_customer);
        tvTime = (TextView) findViewById(R.id.tv_time_booking);
        db= new DBHelper(BookingTableActivity.this);



        //create table data
            if (db.isTableEmpty("Tablee")) {
            for (int i = 1; i <= 20; i++) {
                String amount = "1";
                String sr1 = String.valueOf(i);
                String checkin = "Test";
                String name = "test";
                db.insertTableeData(sr1, amount, false, checkin, name);
            }
        }


        //push data to adapter
        Cursor cursor = db.getTableeData();
        while (cursor.moveToNext()) {
            boolean checkBool = getBooleanValue(cursor, "booked");
            TableBooking obj = new TableBooking(cursor.getString(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), checkBool);
            tableBookingDataHolder.add(obj);
        }
        TableBookingAdapter tableBookingAdapter = new TableBookingAdapter(this, tableBookingDataHolder);
        mrcvBooking.setAdapter(tableBookingAdapter);
    }

//checkBooked
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