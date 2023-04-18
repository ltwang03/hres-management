package edu.huflit.hres_management;

import static android.content.ContentValues.TAG;
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
    LinearLayout lnrBooking;

    ImageView imgEmpty, imgBooking;
    CardView layoutBooking1;
    boolean setVisibility = false;
    TextView tvName, tvAmount,tvTime,tvNumberTable;
    Dialog dialog;

    ArrayList<TableBooking>  tableBookingDataHolder = new ArrayList<>();


    private TableBookingAdapter mTableBookingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(BookingTableActivity.this);
        setContentView(R.layout.activity_booking_table);
        mrcvBooking = findViewById(R.id.rcvBooking);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        mrcvBooking.setLayoutManager(gridLayoutManager);

        layoutBooking1 = (CardView) findViewById(R.id.layout_booking);
        imgEmpty = (ImageView) findViewById(R.id.img_empty);
        imgBooking = (ImageView) findViewById(R.id.img_booking);
        lnrBooking = (LinearLayout) findViewById(R.id.ln_info_booking);
        tvAmount = (TextView) findViewById(R.id.tv_amount_people);
        tvNumberTable = (TextView) findViewById(R.id.number_table);
        tvName = (TextView) findViewById(R.id.tv_name_booking_customer);
        tvTime = (TextView) findViewById(R.id.tv_time_booking);

        db = new DBHelper(this);
        layoutBooking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgEmpty.getVisibility() == View.VISIBLE) {
                    dialogBooking(tvNumberTable,tvName,tvAmount,tvTime,lnrBooking,imgBooking,imgEmpty);
                } else {
                                    Log.e("TAG", "đã đặt ");
                                }
            }
        });



        //create table data
        if(db.isTableEmpty("Tablee")) {
            for(int i = 1 ; i <= 20; i++) {
                String amount = "1";
                String sr1 = String.valueOf(i);
                String checkin = "Test";
                String name = "test";
                db.insertTableeData(sr1,amount,false,checkin,name);
            }
        }
        //push data to adapter
        Cursor cursor = db.getTableeData();
        while (cursor.moveToNext()) {
        boolean checkBool =     getBooleanValue(cursor,"booked");
        TableBooking obj = new TableBooking(cursor.getString(0), cursor.getString(1), cursor.getString(3), cursor.getString(4), checkBool);
        tableBookingDataHolder.add(obj);
        }
        TableBookingAdapter tableBookingAdapter = new TableBookingAdapter(this,tableBookingDataHolder);
        mrcvBooking.setAdapter(tableBookingAdapter);
    }
    public void dialogBooking(TextView tvTableNumber, TextView tvNameBookingCustomer, TextView tvAmountCustomer, TextView tvTimeCheckin, LinearLayout lnInfoBooking, ImageView imgBooking, ImageView imgEmpty) {
        dialog = new Dialog(BookingTableActivity.this);
        dialog.setContentView(R.layout.dialog_form_booking);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button mbtnCancel = dialog.findViewById((R.id.btn_cancel));
        mbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //time check in
        EditText edtTimeCheckin = dialog.findViewById(R.id.time_checkin);
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        edtTimeCheckin.setText(sdf.format(currentTime));

        EditText edtAmountPeople = dialog.findViewById(R.id.amount_people);
        EditText edtNameCustomer = dialog.findViewById(R.id.name_customer);
        Button mbtnBooking = dialog.findViewById(R.id.btn_booking);
        mbtnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tableNumber1 = tvTableNumber.getText().toString();
                String nameCustomer = edtNameCustomer.getText().toString() + " ";
                tvNameBookingCustomer.setText(nameCustomer);
                String amount = edtAmountPeople.getText().toString();
                tvAmountCustomer.setText("SL :" + amount);
                String time = edtTimeCheckin.getText().toString();
                tvTimeCheckin.setText(time);
                setVisibility = isSetVisibility(lnInfoBooking, imgBooking, imgEmpty);
                dialog.dismiss();
//                Log.e(TAG, "onClick: "  + + "/"  +  + "/ " + edtTimeCheckin  );

                db = new DBHelper(BookingTableActivity.this);
                Log.e(TAG, "onClick: " + nameCustomer + amount + time + isSetVisibility(lnInfoBooking, imgBooking, imgEmpty) + tableNumber1);
                boolean checkupdate = db.updateTableeData(tableNumber1, amount, isSetVisibility(lnInfoBooking, imgBooking, imgEmpty), time, nameCustomer);
                if (checkupdate == true) {
                    Toast.makeText(BookingTableActivity.this, "Update succesfull", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BookingTableActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }


            }


        });
        dialog.show();
    }
    public boolean isSetVisibility(LinearLayout lnInfoBooking, ImageView imgBooking, ImageView imgEmpty) {
        if (!setVisibility) {
            lnInfoBooking.setVisibility(View.INVISIBLE);
            imgBooking.setVisibility(View.INVISIBLE);
            imgEmpty.setVisibility(View.VISIBLE);
        }//true
        lnInfoBooking.setVisibility(View.VISIBLE);
        imgBooking.setVisibility(View.VISIBLE);
        imgEmpty.setVisibility(View.INVISIBLE);
        return true;
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
//    private List<TableBooking> getListBooking(){
//        List<TableBooking> list = new ArrayList<>();
//
//      for(int i = 1 ; i <= 20; i++) {
//          String sr  = String.valueOf(i);
//          String amount = "0";
//          list.add(new TableBooking(sr,amount,"Test" , "empty" , false));
//      }
//
//        return list;
//    }
}