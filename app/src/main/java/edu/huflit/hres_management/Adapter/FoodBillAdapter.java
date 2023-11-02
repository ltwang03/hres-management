package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.hres_management.Model.FoodBill;
import edu.huflit.hres_management.R;

public class FoodBillAdapter extends RecyclerView.Adapter<FoodBillAdapter.ViewHolder> {
    private List<FoodBill> mListFoodBill;
    public void setData(List<FoodBill> list){
        this.mListFoodBill=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_foodbill,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodBill foodBill = mListFoodBill.get(position);
        if(foodBill == null)
            return;
        holder.tv_name_food.setText(foodBill.getNameFood());
        holder.tv_amount_food.setText(String.valueOf(foodBill.getAmountFood()));
        holder.tv_total_item.setText(String.valueOf(foodBill.getPriceFood()));

    }

    @Override
    public int getItemCount() {
        if(mListFoodBill != null)
            return mListFoodBill.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ordinal_numbers,tv_name_food,tv_amount_food,tv_total_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ordinal_numbers = itemView.findViewById(R.id.ordinal_numbers);
            tv_name_food = itemView.findViewById(R.id.name_food);
            tv_amount_food = itemView.findViewById(R.id.amount_food);
            tv_total_item = itemView.findViewById(R.id.total_item);

        }
    }
}
