package edu.huflit.hres_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.huflit.hres_management.Model.Drinks;
import edu.huflit.hres_management.R;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>{
    private Context mContext;
    private List<Drinks> mListDrinks;

    public DrinksAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Drinks> list){
        this.mListDrinks= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_food,parent,false);

        return new DrinksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksViewHolder holder, int position) {

        Drinks drinks = mListDrinks.get(position);
        if(drinks == null)
            return;
        holder.imgFood.setImageResource(drinks.getResourceId());
        holder.tvName.setText(drinks.getName());
        holder.tvDescribe.setText(drinks.getDescribe());
        holder.tvPrice.setText(String.valueOf(drinks.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(mListDrinks != null){
            return mListDrinks.size();
        }
        return 0;
    }

    public class DrinksViewHolder extends RecyclerView.ViewHolder{


        private final ImageView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;

        public DrinksViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
