package edu.huflit.hres_management.Adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.TableOrder;
import edu.huflit.hres_management.OrderFoodActivity;
import edu.huflit.hres_management.R;

public class TableOrderAdapter  extends RecyclerView.Adapter<TableOrderAdapter.TableOrderViewHolder>
{
    private Context mContext;
    private ArrayList<TableOrder> arrayList;
    DBHelper db;
    Dialog dialog;
    public TableOrderAdapter(Context mContext,ArrayList<TableOrder> arrayList) {
        super();
        this.mContext = mContext;
        this.arrayList = arrayList;

    }


    @NonNull
    @Override
    public TableOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.layout_order, parent, false);
        return new TableOrderViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull TableOrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TableOrder tableOrder = arrayList.get(position);
        if (tableOrder == null) {
            return;
        }
        Log.e(TAG, "onBindViewHolder: " + arrayList.get(position).getNumberTable() );
        holder.tvTalbeNumber.setText(arrayList.get(position).getNumberTable());
        holder.tvName.setText(arrayList.get(position).getNameCustomer());
        holder.tvAmount.setText(arrayList.get(position).getAmountCustomer());
        holder.tvTime.setText(arrayList.get(position).getTimeCheckin());
        boolean checkbooked = arrayList.get(position).isBooked();

        holder.imgvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tableNumber1 = arrayList.get(position).getNumberTable();
                String nameCustomer = arrayList.get(position).getNameCustomer();
                String amountCustomer=  arrayList.get(position).getAmountCustomer();
                String timeCheckinOrder = arrayList.get(position).getTimeCheckin();
                Log.d(TAG, "onClick: " + tableNumber1 + nameCustomer+amountCustomer+timeCheckinOrder);
                handleDialog(tableNumber1,nameCustomer,amountCustomer,timeCheckinOrder,holder.ln_info_order,holder.imgvOrder);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public class TableOrderViewHolder extends RecyclerView.ViewHolder{

    TextView tvName,tvAmount,tvTime,tvTalbeNumber;

    ImageView imgvOrder;
    ScrollView srcView;
    LinearLayout ln_info_order;


        public TableOrderViewHolder(@NonNull View itemView){
            super(itemView);
            tvName= itemView.findViewById(R.id.tv_name_customer_order);
            tvAmount = itemView.findViewById(R.id.tv_amount_people_order);
            tvTime = itemView.findViewById(R.id.tv_time_order);
//            tvAccesableTable= itemView.findViewById(R.id.accesableTable);
            tvTalbeNumber = itemView.findViewById(R.id.number_table_order);
            imgvOrder = itemView.findViewById(R.id.imgv_order);
            ln_info_order = itemView.findViewById(R.id.ln_info_order);
            srcView = itemView.findViewById(R.id.scrollViewOrder);

        }
    }
    public void handleDialog(String tableNumber1,String nameCustomer,String amountCustomer,String timeCheckinOrder, LinearLayout lnInfoOrder, ImageView imgOrder) {
        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.order_dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button mbtnInBill = dialog.findViewById((R.id.btn_bill));
        db = new DBHelper(mContext);



        mbtnInBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView tvTimeOrder = dialog.findViewById(R.id.time_checkin_form_order);
        tvTimeOrder.setText(timeCheckinOrder);
        TextView tvOrderAmountCustomer = dialog.findViewById(R.id.tv_amount_people_order);
        tvOrderAmountCustomer.setText(amountCustomer);
        TextView tvOrderName = dialog.findViewById(R.id.name_customer_form_order);
        tvOrderName.setText(nameCustomer);
        Button mbtnOrder = dialog.findViewById(R.id.btn_order);








        mbtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = mContext.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("customer_name", nameCustomer);
                editor.putString("amount_customer" , amountCustomer);
                editor.putString("table_number",tableNumber1);
                editor.putString("time_checkin" , timeCheckinOrder);
                editor.apply();

                Intent i = new Intent(mContext, OrderFoodActivity.class);
                mContext.startActivity(i);
            }
        });

        dialog.show();

    }


}
