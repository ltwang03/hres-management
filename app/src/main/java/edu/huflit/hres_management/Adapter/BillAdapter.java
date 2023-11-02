package edu.huflit.hres_management.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
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

import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.Bill;
import edu.huflit.hres_management.Model.FoodBill;
import edu.huflit.hres_management.R;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ListBillViewHolder> {
    private Context mContext;
    private List<Bill> mListBill;
    List<FoodBill> listFoodBill;
    private int price =0;


    boolean Show = false;
    DBHelper db ;
    public BillAdapter(Context mContext, List<Bill> mListBill ,List<FoodBill> mListFoodBill) {
        this.mContext = mContext;
        this.mListBill = mListBill;
        this.listFoodBill = mListFoodBill;
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

    db =new DBHelper(mContext);
        Cursor cursor1 = db.getPaidFood();
        Log.e(TAG, "onBindViewHolder: " + bill.getTableNumber());
        while (cursor1.moveToNext()) {
            if(cursor1.getString(1).equals(bill.getTableNumber()) && cursor1.getInt(5) != 0 && cursor1.getString(2).equals(bill.getTimeCheckIn())) {

                listFoodBill.add(new FoodBill(cursor1.getInt(5),cursor1.getString(3),cursor1.getInt(4)));
                Log.e(TAG, "onBindViewHolder: " + cursor1.getInt(5) + cursor1.getInt(4) );
                price+=cursor1.getInt(5) * cursor1.getInt(4);

            }

        }
        foodBillAdapter.setData(listFoodBill);
        holder.rcvListFoodBill.setLayoutManager(linearLayoutManager);

        holder.tv_name_customer.setText(bill.getNameCustomer());
        holder.tv_time_checkout.setText(bill.getTimeCheckIn());
//        holder.tv_price_total.setText(bill.getPriceTotal().toString());
        holder.tv_price_total.setText(String.valueOf(price));
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
