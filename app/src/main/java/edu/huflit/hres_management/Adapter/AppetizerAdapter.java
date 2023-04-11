package edu.huflit.hres_management.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.hres_management.Model.Appetizer;
import edu.huflit.hres_management.R;

public class AppetizerAdapter extends RecyclerView.Adapter<AppetizerAdapter.AppetizerViewHolder>{
    private Context mContext;
    private ArrayList<Appetizer> mListAppetizer;

    public AppetizerAdapter(ArrayList<Appetizer> list) {
        this.mContext = mContext; this.mListAppetizer= list;
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

        Appetizer appetizer = mListAppetizer.get(position);
        if(appetizer == null)
            return;
//        holder.imgFood.setImageResource(food1.getResourceId());

        Picasso.get().load(mListAppetizer.get(position).getResourceId()).into(holder.imgFoodReal);
//        holder.imgFood.setText(mListAppetizer.get(position).getResourceId());
        holder.tvName.setText(mListAppetizer.get(position).getName());
        holder.tvDescribe.setText(mListAppetizer.get(position).getDescribe());
        holder.tvPrice.setText(mListAppetizer.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        if(mListAppetizer != null){
            return mListAppetizer.size();
        }
        return 0;
    }

    public class AppetizerViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoodReal;
        private TextView imgFood;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvPrice;

        public AppetizerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodReal =itemView.findViewById(R.id.img_food_real);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.name_food);
            tvDescribe = itemView.findViewById(R.id.describe_food);
            tvPrice = itemView.findViewById(R.id.price_food);
        }
    }

}
