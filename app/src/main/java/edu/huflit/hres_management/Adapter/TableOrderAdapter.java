package edu.huflit.hres_management.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.TableOrder;
import edu.huflit.hres_management.R;

public class TableOrderAdapter  extends RecyclerView.Adapter<TableOrderAdapter.TableOrderViewHolder>
{
    private Context mContext;
    private ArrayList<TableOrder> arrayList;
    DBHelper db;
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
    public void onBindViewHolder(@NonNull TableOrderViewHolder holder, int position) {
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

    TextView tvName,tvAmount,tvTime,tvAccesableTable,tvTalbeNumber;

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
    public void handleDialog() {

    }


}
