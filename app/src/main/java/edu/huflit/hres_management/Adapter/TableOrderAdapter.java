package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Model.TableOrder;
import edu.huflit.hres_management.R;

public class TableOrderAdapter  extends RecyclerView.Adapter<TableOrderAdapter.TableOrderViewHolder>
{
    Context mContext;
    ArrayList<TableOrder> arrayList;
    public TableOrderAdapter() {
        super();
    }


    @NonNull
    @Override
    public TableOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.layout_order, parent, false);

        return new TableOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableOrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TableOrderViewHolder extends RecyclerView.ViewHolder{

    TextView tvName,tvAmount,tvTime,tvAccesableTable,tvTalbeNumber;

    ImageView imgvOrder;
    LinearLayout ln_info_order;


        public TableOrderViewHolder(View itemView){
            super(itemView);
            tvName= itemView.findViewById(R.id.tv_name_booking_customer_order);
            tvAmount = itemView.findViewById(R.id.tv_amount_people_order);
            tvTime = itemView.findViewById(R.id.tv_time_order);
            tvAccesableTable= itemView.findViewById(R.id.accesableTable);
            tvTalbeNumber = itemView.findViewById(R.id.number_table_order);
            imgvOrder = itemView.findViewById(R.id.imgv_order);
            ln_info_order = itemView.findViewById(R.id.ln_info_order);

        }
    }


}
