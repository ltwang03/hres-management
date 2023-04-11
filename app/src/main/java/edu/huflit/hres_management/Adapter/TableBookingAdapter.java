package edu.huflit.hres_management.Adapter;

import android.app.Dialog;
import android.content.Context;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.huflit.hres_management.Model.TableBooking;
import edu.huflit.hres_management.R;

public class TableBookingAdapter extends RecyclerView.Adapter<TableBookingAdapter.TableBookingViewHolder> {
    private Context mContext;
    private List<TableBooking> mListTable;
    Dialog dialog;
    boolean setVisibility = false;

    public TableBookingAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<TableBooking> list){
        this.mListTable = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TableBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.layout_table_booking,parent,false);

        return new TableBookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableBookingViewHolder holder, int position) {
        TableBooking tableBooking = mListTable.get(position);
        if(tableBooking == null){
            return;
        }
        holder.tvNumberTable.setText(String.valueOf(tableBooking.getNumberTable()));
        holder.tvAmountCustomer.setText(String.valueOf(tableBooking.getAmountCustomer()));
        holder.tvTimeCheckin.setText(tableBooking.getTimeCheckin());
        holder.lnInfoBooking.setVisibility(View.INVISIBLE);
        holder.imgBooking.setVisibility(View.INVISIBLE);
        holder.imgEmpty.setVisibility(View.VISIBLE);
        holder.layoutBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.imgEmpty.getVisibility() == View.VISIBLE){

                    dialogBooking(holder.tvAmountCustomer, holder.tvTimeCheckin,holder.lnInfoBooking,holder.imgBooking,holder.imgEmpty);
                }
                else {
                    Log.e("TAG", "đã đặt " );
                }
            }
        });
    }
    public void dialogBooking(TextView tvAmountCustomer,TextView tvTimeCheckin,LinearLayout lnInfoBooking,ImageView imgBooking,ImageView imgEmpty){
        dialog = new Dialog(mContext);
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
        Button mbtnBooking = dialog.findViewById(R.id.btn_booking);
        mbtnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String amountPeopleStr = edtAmountPeople.getText().toString();
                int amountPeople = Integer.parseInt(amountPeopleStr);
                tvAmountCustomer.setText(String.valueOf(amountPeople));
                tvTimeCheckin.setText(edtTimeCheckin.getText().toString());
                setVisibility = isSetVisibility(lnInfoBooking,imgBooking,imgEmpty);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public boolean isSetVisibility(LinearLayout lnInfoBooking,ImageView imgBooking,ImageView imgEmpty){
        if(!setVisibility){
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
        if(mListTable!=null){
            return mListTable.size();
        }
        return 0;
    }

    public class TableBookingViewHolder extends RecyclerView.ViewHolder{
        private CardView layoutBooking;
        private TextView tvNumberTable;
        private TextView tvAmountCustomer;
        private TextView tvTimeCheckin;
         LinearLayout lnInfoBooking;
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

        }
    }


}
