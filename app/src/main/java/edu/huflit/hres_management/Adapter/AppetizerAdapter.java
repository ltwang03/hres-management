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

import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.R;

public class AppetizerAdapter extends RecyclerView.Adapter<AppetizerAdapter.AppetizerViewHolder>{
    private Context mContext;
    private List<Appetizer> mListAppetizer;

    public AppetizerAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Appetizer> list){
        this.mListAppetizer= list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public AppetizerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_food,parent,false);

        return new AppetizerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppetizerViewHolder holder, int position) {

        Appetizer food1 = mListAppetizer.get(position);
        if(food1 == null)
            return;
        holder.imgFood.setImageResource(food1.getResourceId());
        holder.tvName.setText(food1.getName());
        holder.tvDescribe.setText(food1.getDescribe());
        holder.tvPrice.setText(String.valueOf(food1.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(mListAppetizer != null){
            return mListAppetizer.size();
        }
        return 0;
    }

    public class AppetizerViewHolder extends RecyclerView.ViewHolder{


        private ImageView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;

        public AppetizerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
