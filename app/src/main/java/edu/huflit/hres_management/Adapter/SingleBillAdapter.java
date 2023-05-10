package edu.huflit.hres_management.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.huflit.hres_management.Database.DBHelper;
import edu.huflit.hres_management.Model.FoodBill;
import edu.huflit.hres_management.R;

public class SingleBillAdapter extends RecyclerView.Adapter<SingleBillAdapter.SingleBillViewHolder> {
    Context mContext;
    ArrayList<FoodBill> mFoodBill;
    int total,amount_product,price,totalSinglePrice;
    DBHelper db;
    public SingleBillAdapter(Context mContext,ArrayList<FoodBill> mFoodBill) {
        this.mContext = mContext;
        this.mFoodBill = mFoodBill;
    }
    @NonNull
    @Override
    public SingleBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_bill, parent,false);

        return new SingleBillAdapter.SingleBillViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SingleBillViewHolder holder, int position) {
        DecimalFormat formater = new DecimalFormat("#,### đ");
        holder.tvNameProduct.setText(mFoodBill.get(position).getNameFood());
        ///holder.tvAmountProduct.setText(mFoodBill.get(position).getAmountFood());
        int id = mFoodBill.get(position).getAmountFood();
        String StrId = String.valueOf(id);

        holder.tvAmountProduct.setText(StrId);
        amount_product = mFoodBill.get(position).getAmountFood();

        String StrPrice= String.valueOf(mFoodBill.get(position).getPriceFood());
        holder.tvPriceProduct.setText(StrPrice);
        price = mFoodBill.get(position).getPriceFood();
        totalSinglePrice = price * amount_product;
        String formatedStr = formater.format(totalSinglePrice);
        holder.tvtotalSingleProduct.setText(String.valueOf(formatedStr));


    }

    @Override
    public int getItemCount() {
        return mFoodBill.size();
    }

    public class SingleBillViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameProduct,tvAmountProduct,tvPriceProduct,tvTableNumber,tvAmountPeopleBill,tvTimeCheckin,tvTotalPrice,tvNameCustomer,tvtotalSingleProduct;
        private Button btnPrintBill,btnCancel;
        public  SingleBillViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameProduct = itemView.findViewById(R.id.tvProduct_name_bill);
            tvAmountProduct = itemView.findViewById(R.id.amount_item_food_bill);
            tvPriceProduct = itemView.findViewById(R.id.tvPriceFoodBill);
            tvTableNumber = itemView.findViewById(R.id.tv_numberTableBill);
            tvNameCustomer= itemView.findViewById(R.id.tv_nameCusBill);
            tvAmountPeopleBill = itemView.findViewById(R.id.tv_amountCusBill);
            tvTimeCheckin = itemView.findViewById(R.id.tv_timeCheckInBill);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvtotalSingleProduct=  itemView.findViewById(R.id.total_single_price);


        }
    }
}

