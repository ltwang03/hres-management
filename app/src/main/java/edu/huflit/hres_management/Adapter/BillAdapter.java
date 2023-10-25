package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.hres_management.Model.Bill;
import edu.huflit.hres_management.Model.FoodBill;
import edu.huflit.hres_management.R;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ListBillViewHolder> {
    private Context mContext;
    private List<Bill> mListBill;
    boolean Show = false;
    public BillAdapter(Context mContext, List<Bill> mListBill) {
        this.mContext = mContext;
        this.mListBill = mListBill;
    }

    @NonNull
    @Override
    public ListBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bill,parent,false);
        return new ListBillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBillViewHolder holder, int position) {
        final Bill bill = mListBill.get(position);
        if(bill == null)
            return;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        FoodBillAdapter foodBillAdapter=new FoodBillAdapter();
        foodBillAdapter.setData(bill.getListFoodBill());
        holder.rcvListFoodBill.setLayoutManager(linearLayoutManager);

        holder.tv_name_customer.setText(bill.getNameCustomer());
//        holder.tv_time_checkout.setText(bill.getTimeCheckout());
//        holder.tv_price_total.setText(bill.getPriceTotal().toString());
        holder.rcvListFoodBill.setAdapter(foodBillAdapter);
        holder.billMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisible(bill, holder.billShow);
            }
        });
    }
    private void setVisible(Bill bill, LinearLayout billShow){
        if(Show == false)
        {
            billShow.setVisibility(View.VISIBLE);
            Show = true;
        }else {
            billShow.setVisibility(View.GONE);
            Show = false;
        }

    }

    @Override
    public int getItemCount() {
        if(mListBill != null)
            return mListBill.size();
        return 0;
    }

    public class ListBillViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView rcvListFoodBill;
        private TextView tv_name_customer, tv_time_checkout,tv_price_total;
        private LinearLayout billMain, billShow;

        public ListBillViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvListFoodBill = itemView.findViewById(R.id.rcv_Itembill);
            tv_name_customer = itemView.findViewById(R.id.name_customer);
            tv_time_checkout = itemView.findViewById(R.id.time_checkout);
            tv_price_total = itemView.findViewById(R.id.price_total);
            billMain = itemView.findViewById(R.id.bill_main);
            billShow = itemView.findViewById(R.id.bill_show);

        }
    }
}
