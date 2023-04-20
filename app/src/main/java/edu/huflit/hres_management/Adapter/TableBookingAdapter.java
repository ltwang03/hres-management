package edu.huflit.hres_management.Adapter;

import static android.content.ContentValues.TAG;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.huflit.hres_management.BookingTableActivity;
import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.TableBooking;
import edu.huflit.hres_management.R;

public class TableBookingAdapter extends RecyclerView.Adapter<TableBookingAdapter.TableBookingViewHolder> {
    private Context mContext;
    private Activity mActivity;
    private ArrayList<TableBooking> mListTable;
    Dialog dialog;
    DBHelper db;

    boolean setVisibility = false;

    public TableBookingAdapter(Context mContext, ArrayList<TableBooking> list) {
        this.mContext = mContext;
        this.mListTable = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TableBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.layout_table_booking, parent, false);
        return new TableBookingViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TableBookingViewHolder holder, int position) {
        TableBooking tableBooking = mListTable.get(position);
        if (tableBooking == null) {
            return;
        }

                        holder.tvNumberTable.setText(mListTable.get(position).getNumberTable());
                        holder.tvNameBookingCustomer.setText(mListTable.get(position).getNameCustomer());
                        holder.tvAmountCustomer.setText(mListTable.get(position).getAmountCustomer());
                        holder.tvTimeCheckin.setText(mListTable.get(position).getTimeCheckin());
                        boolean checkbooked = mListTable.get(position).isBooked();
                        db = new DBHelper(mContext);

                        Cursor cursor = db.getTableeData();
                        while(cursor.moveToNext()) {
                            if(checkbooked == true) {
                                holder.lnInfoBooking.setVisibility(View.VISIBLE);
                                holder.imgBooking.setVisibility(View.VISIBLE);
                                holder.imgEmpty.setVisibility(View.INVISIBLE);

                            } else if (checkbooked ==false) {
                                holder.lnInfoBooking.setVisibility(View.INVISIBLE);
                                holder.imgBooking.setVisibility(View.INVISIBLE);
                                holder.imgEmpty.setVisibility(View.VISIBLE);
                            }


        }
                        db = new DBHelper(mContext);


                        holder.layoutBooking.setOnClickListener(new View.OnClickListener() {
                            @Override

                            public void onClick(View view) {



                                if (holder.imgEmpty.getVisibility() == View.VISIBLE) {

                                    dialogBooking(holder.tvNumberTable, holder.tvNameBookingCustomer, holder.tvAmountCustomer, holder.tvTimeCheckin, holder.lnInfoBooking, holder.imgBooking, holder.imgEmpty);



                                } else {
                                    Log.e("TAG", "đã đặt ");
                                }




                            }
                        });
                    }



    public void dialogBooking(TextView tvTableNumber, TextView tvNameBookingCustomer, TextView tvAmountCustomer, TextView tvTimeCheckin, LinearLayout lnInfoBooking, ImageView imgBooking, ImageView imgEmpty) {
        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_form_booking);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button mbtnCancel = dialog.findViewById((R.id.btn_cancel));db = new DBHelper(mContext);



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

                db = new DBHelper(mContext);
                Log.e(TAG, "onClick: " + nameCustomer + amount + time + isSetVisibility(lnInfoBooking, imgBooking, imgEmpty) + tableNumber1);
                boolean checkupdate = db.updateTableeData(tableNumber1, amount, isSetVisibility(lnInfoBooking, imgBooking, imgEmpty), time, nameCustomer);
                if (checkupdate == true) {
                    Toast.makeText(mContext, "Update succesfull", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Update failed", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(mContext,BookingTableActivity.class);
                mContext.startActivity(i);

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

    @Override
    public int getItemCount() {
        if (mListTable != null) {
            return mListTable.size();
        }
        return 0;
    }

    public class TableBookingViewHolder extends RecyclerView.ViewHolder {
        private CardView layoutBooking;
        private TextView tvNumberTable;
        private TextView tvAmountCustomer;
        private TextView tvTimeCheckin;
        LinearLayout lnInfoBooking;
        TextView tvNameBookingCustomer;
        ImageView imgBooking;

        ImageView imgEmpty;

        public TableBookingViewHolder(@NonNull View itemView) {
            super(itemView);
            lnInfoBooking = itemView.findViewById(R.id.ln_info_booking);
            imgBooking = itemView.findViewById(R.id.img_booking);
            imgEmpty = itemView.findViewById(R.id.img_empty);
            layoutBooking = itemView.findViewById(R.id.layout_booking);
            tvNumberTable = itemView.findViewById(R.id.number_table);
            tvAmountCustomer = itemView.findViewById(R.id.tv_amount_people);
            tvTimeCheckin = itemView.findViewById(R.id.tv_time_booking);
            tvNameBookingCustomer = itemView.findViewById(R.id.tv_name_booking_customer);


        }
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
