package edu.huflit.hres_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Food1Adapter extends RecyclerView.Adapter<Food1Adapter.Food1ViewHolder>{
    private Context mContext;
    private List<Food1> mListFood1;

    public Food1Adapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Food1> list){
        this.mListFood1= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public Food1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_listfood,parent,false);

        return new Food1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Food1ViewHolder holder, int position) {

        Food1 food1 = mListFood1.get(position);
        if(food1 == null)
            return;
        holder.imgFood.setImageResource(food1.getResourceId());
        holder.tvName.setText(food1.getName());
        holder.tvDescribe.setText(food1.getDescribe());
        holder.tvPrice.setText(food1.getPrice());
    }

    @Override
    public int getItemCount() {
        if(mListFood1 != null){
            return mListFood1.size();
        }
        return 0;
    }

    public class Food1ViewHolder extends RecyclerView.ViewHolder{


        private ImageView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;

        public Food1ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
