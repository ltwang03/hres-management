package edu.huflit.hres_management;

import static edu.huflit.hres_management.R.menu.popup_booking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Calendar;
import java.util.Date;

public class BookingTableActivity extends AppCompatActivity {

    TextView tvAmountPeople,tvTimeCheckIn;
    RelativeLayout rltTable1;
    ImageView imgEmpty1,imgBooking1;
    LinearLayout lnInfoBooking1;
    Button mbtnCancel,mbtnBooking;
    EditText edtAmountPeople,etTimeCheckin;
    AlertDialog dialog;

    boolean setVisibleTable1 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_table);
        View view1 = getLayoutInflater().inflate(R.layout.dialog_form_booking,null);

        tvAmountPeople =findViewById(R.id.tv_amount_people1);
        tvTimeCheckIn = findViewById(R.id.tv_time_booking);
        rltTable1=findViewById(R.id.table_1);
        imgEmpty1 = findViewById(R.id.img_empty1);
        imgBooking1 = findViewById(R.id.img_booking1);
        lnInfoBooking1= findViewById(R.id.ln_info_booking1);
        //khai báo cho dialog
        mbtnCancel = view1.findViewById((R.id.btn_cancel));
        mbtnBooking = view1.findViewById(R.id.btn_booking);
        etTimeCheckin = view1.findViewById(R.id.time_checkin);
        edtAmountPeople = view1.findViewById(R.id.amount_people);
        //nếu số lượng khách từ 1 trở lên thì hiện thị đã booking
//        if(Integer.parseInt(tvAmountPeople.getText().toString())>0) {
//            setVisibleTable1 = true;
//        }
//

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view1);
        dialog = builder.create();
        PopupMenu popupMenu = new PopupMenu(this,rltTable1);
        mbtnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                etTimeCheckin.setText(sdf.format(currentTime));
                tvTimeCheckIn.setText(etTimeCheckin.getText().toString());
                tvAmountPeople.setText(edtAmountPeople.getText().toString());
                setVisibleTable();
                dialog.dismiss();

            }
        });

        mbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        rltTable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }
    private boolean setVisibleTable(){
        if(setVisibleTable1==true){
            lnInfoBooking1.setVisibility(View.VISIBLE);
            imgBooking1.setVisibility(View.VISIBLE);
            imgEmpty1.setVisibility(View.INVISIBLE);
        }
        else {
            lnInfoBooking1.setVisibility(View.VISIBLE);
            imgBooking1.setVisibility(View.VISIBLE);
            imgEmpty1.setVisibility(View.INVISIBLE);
        }
        return false;
    }
}